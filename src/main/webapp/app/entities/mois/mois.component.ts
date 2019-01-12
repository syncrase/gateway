import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMois } from 'app/shared/model/mois.model';
import { AccountService } from 'app/core';
import { MoisService } from './mois.service';

@Component({
    selector: 'jhi-mois',
    templateUrl: './mois.component.html'
})
export class MoisComponent implements OnInit, OnDestroy {
    mois: IMois[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected moisService: MoisService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.moisService.query().subscribe(
            (res: HttpResponse<IMois[]>) => {
                this.mois = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMois();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMois) {
        return item.id;
    }

    registerChangeInMois() {
        this.eventSubscriber = this.eventManager.subscribe('moisListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
