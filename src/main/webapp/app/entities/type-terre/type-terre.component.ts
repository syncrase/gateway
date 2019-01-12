import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITypeTerre } from 'app/shared/model/type-terre.model';
import { AccountService } from 'app/core';
import { TypeTerreService } from './type-terre.service';

@Component({
    selector: 'jhi-type-terre',
    templateUrl: './type-terre.component.html'
})
export class TypeTerreComponent implements OnInit, OnDestroy {
    typeTerres: ITypeTerre[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected typeTerreService: TypeTerreService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.typeTerreService.query().subscribe(
            (res: HttpResponse<ITypeTerre[]>) => {
                this.typeTerres = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTypeTerres();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITypeTerre) {
        return item.id;
    }

    registerChangeInTypeTerres() {
        this.eventSubscriber = this.eventManager.subscribe('typeTerreListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
