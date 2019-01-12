import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITypeFeuillage } from 'app/shared/model/type-feuillage.model';
import { TypeFeuillageService } from './type-feuillage.service';
import { IPlante } from 'app/shared/model/plante.model';
import { PlanteService } from 'app/entities/plante';

@Component({
    selector: 'jhi-type-feuillage-update',
    templateUrl: './type-feuillage-update.component.html'
})
export class TypeFeuillageUpdateComponent implements OnInit {
    typeFeuillage: ITypeFeuillage;
    isSaving: boolean;

    plantes: IPlante[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected typeFeuillageService: TypeFeuillageService,
        protected planteService: PlanteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeFeuillage }) => {
            this.typeFeuillage = typeFeuillage;
        });
        this.planteService.query().subscribe(
            (res: HttpResponse<IPlante[]>) => {
                this.plantes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.typeFeuillage.id !== undefined) {
            this.subscribeToSaveResponse(this.typeFeuillageService.update(this.typeFeuillage));
        } else {
            this.subscribeToSaveResponse(this.typeFeuillageService.create(this.typeFeuillage));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeFeuillage>>) {
        result.subscribe((res: HttpResponse<ITypeFeuillage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPlanteById(index: number, item: IPlante) {
        return item.id;
    }
}
