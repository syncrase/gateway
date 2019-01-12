import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFloraison } from 'app/shared/model/floraison.model';
import { AccountService } from 'app/core';
import { FloraisonService } from './floraison.service';

@Component({
    selector: 'jhi-floraison',
    templateUrl: './floraison.component.html'
})
export class FloraisonComponent implements OnInit, OnDestroy {
    floraisons: IFloraison[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected floraisonService: FloraisonService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.floraisonService.query().subscribe(
            (res: HttpResponse<IFloraison[]>) => {
                this.floraisons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFloraisons();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFloraison) {
        return item.id;
    }

    registerChangeInFloraisons() {
        this.eventSubscriber = this.eventManager.subscribe('floraisonListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
