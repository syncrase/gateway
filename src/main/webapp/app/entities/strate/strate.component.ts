import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStrate } from 'app/shared/model/strate.model';
import { AccountService } from 'app/core';
import { StrateService } from './strate.service';

@Component({
    selector: 'jhi-strate',
    templateUrl: './strate.component.html'
})
export class StrateComponent implements OnInit, OnDestroy {
    strates: IStrate[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected strateService: StrateService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.strateService.query().subscribe(
            (res: HttpResponse<IStrate[]>) => {
                this.strates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStrates();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IStrate) {
        return item.id;
    }

    registerChangeInStrates() {
        this.eventSubscriber = this.eventManager.subscribe('strateListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
