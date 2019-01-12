import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVitesseCroissance } from 'app/shared/model/vitesse-croissance.model';
import { AccountService } from 'app/core';
import { VitesseCroissanceService } from './vitesse-croissance.service';

@Component({
    selector: 'jhi-vitesse-croissance',
    templateUrl: './vitesse-croissance.component.html'
})
export class VitesseCroissanceComponent implements OnInit, OnDestroy {
    vitesseCroissances: IVitesseCroissance[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected vitesseCroissanceService: VitesseCroissanceService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.vitesseCroissanceService.query().subscribe(
            (res: HttpResponse<IVitesseCroissance[]>) => {
                this.vitesseCroissances = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVitesseCroissances();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVitesseCroissance) {
        return item.id;
    }

    registerChangeInVitesseCroissances() {
        this.eventSubscriber = this.eventManager.subscribe('vitesseCroissanceListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
