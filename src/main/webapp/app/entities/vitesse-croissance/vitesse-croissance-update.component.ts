import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IVitesseCroissance } from 'app/shared/model/vitesse-croissance.model';
import { VitesseCroissanceService } from './vitesse-croissance.service';
import { IPlante } from 'app/shared/model/plante.model';
import { PlanteService } from 'app/entities/plante';

@Component({
    selector: 'jhi-vitesse-croissance-update',
    templateUrl: './vitesse-croissance-update.component.html'
})
export class VitesseCroissanceUpdateComponent implements OnInit {
    vitesseCroissance: IVitesseCroissance;
    isSaving: boolean;

    plantes: IPlante[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected vitesseCroissanceService: VitesseCroissanceService,
        protected planteService: PlanteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vitesseCroissance }) => {
            this.vitesseCroissance = vitesseCroissance;
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
        if (this.vitesseCroissance.id !== undefined) {
            this.subscribeToSaveResponse(this.vitesseCroissanceService.update(this.vitesseCroissance));
        } else {
            this.subscribeToSaveResponse(this.vitesseCroissanceService.create(this.vitesseCroissance));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IVitesseCroissance>>) {
        result.subscribe((res: HttpResponse<IVitesseCroissance>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
