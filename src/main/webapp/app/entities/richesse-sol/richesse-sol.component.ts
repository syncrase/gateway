import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRichesseSol } from 'app/shared/model/richesse-sol.model';
import { AccountService } from 'app/core';
import { RichesseSolService } from './richesse-sol.service';

@Component({
    selector: 'jhi-richesse-sol',
    templateUrl: './richesse-sol.component.html'
})
export class RichesseSolComponent implements OnInit, OnDestroy {
    richesseSols: IRichesseSol[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected richesseSolService: RichesseSolService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.richesseSolService.query().subscribe(
            (res: HttpResponse<IRichesseSol[]>) => {
                this.richesseSols = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRichesseSols();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRichesseSol) {
        return item.id;
    }

    registerChangeInRichesseSols() {
        this.eventSubscriber = this.eventManager.subscribe('richesseSolListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
