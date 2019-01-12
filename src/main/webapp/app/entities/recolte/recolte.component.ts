import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRecolte } from 'app/shared/model/recolte.model';
import { AccountService } from 'app/core';
import { RecolteService } from './recolte.service';

@Component({
    selector: 'jhi-recolte',
    templateUrl: './recolte.component.html'
})
export class RecolteComponent implements OnInit, OnDestroy {
    recoltes: IRecolte[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected recolteService: RecolteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.recolteService.query().subscribe(
            (res: HttpResponse<IRecolte[]>) => {
                this.recoltes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRecoltes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRecolte) {
        return item.id;
    }

    registerChangeInRecoltes() {
        this.eventSubscriber = this.eventManager.subscribe('recolteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
