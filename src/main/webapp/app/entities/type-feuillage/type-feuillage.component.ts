import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITypeFeuillage } from 'app/shared/model/type-feuillage.model';
import { AccountService } from 'app/core';
import { TypeFeuillageService } from './type-feuillage.service';

@Component({
    selector: 'jhi-type-feuillage',
    templateUrl: './type-feuillage.component.html'
})
export class TypeFeuillageComponent implements OnInit, OnDestroy {
    typeFeuillages: ITypeFeuillage[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected typeFeuillageService: TypeFeuillageService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.typeFeuillageService.query().subscribe(
            (res: HttpResponse<ITypeFeuillage[]>) => {
                this.typeFeuillages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTypeFeuillages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITypeFeuillage) {
        return item.id;
    }

    registerChangeInTypeFeuillages() {
        this.eventSubscriber = this.eventManager.subscribe('typeFeuillageListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
