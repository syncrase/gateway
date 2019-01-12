import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITypeRacine } from 'app/shared/model/type-racine.model';
import { AccountService } from 'app/core';
import { TypeRacineService } from './type-racine.service';

@Component({
    selector: 'jhi-type-racine',
    templateUrl: './type-racine.component.html'
})
export class TypeRacineComponent implements OnInit, OnDestroy {
    typeRacines: ITypeRacine[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected typeRacineService: TypeRacineService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.typeRacineService.query().subscribe(
            (res: HttpResponse<ITypeRacine[]>) => {
                this.typeRacines = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTypeRacines();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITypeRacine) {
        return item.id;
    }

    registerChangeInTypeRacines() {
        this.eventSubscriber = this.eventManager.subscribe('typeRacineListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
