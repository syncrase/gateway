import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMois } from 'app/shared/model/mois.model';
import { MoisService } from './mois.service';
import { IRecolte } from 'app/shared/model/recolte.model';
import { RecolteService } from 'app/entities/recolte';
import { IFloraison } from 'app/shared/model/floraison.model';
import { FloraisonService } from 'app/entities/floraison';

@Component({
    selector: 'jhi-mois-update',
    templateUrl: './mois-update.component.html'
})
export class MoisUpdateComponent implements OnInit {
    mois: IMois;
    isSaving: boolean;

    recoltes: IRecolte[];

    floraisons: IFloraison[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected moisService: MoisService,
        protected recolteService: RecolteService,
        protected floraisonService: FloraisonService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mois }) => {
            this.mois = mois;
        });
        this.recolteService.query().subscribe(
            (res: HttpResponse<IRecolte[]>) => {
                this.recoltes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.floraisonService.query().subscribe(
            (res: HttpResponse<IFloraison[]>) => {
                this.floraisons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.mois.id !== undefined) {
            this.subscribeToSaveResponse(this.moisService.update(this.mois));
        } else {
            this.subscribeToSaveResponse(this.moisService.create(this.mois));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMois>>) {
        result.subscribe((res: HttpResponse<IMois>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRecolteById(index: number, item: IRecolte) {
        return item.id;
    }

    trackFloraisonById(index: number, item: IFloraison) {
        return item.id;
    }
}
