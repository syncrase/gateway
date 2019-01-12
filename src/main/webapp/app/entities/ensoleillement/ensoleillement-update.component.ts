import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEnsoleillement } from 'app/shared/model/ensoleillement.model';
import { EnsoleillementService } from './ensoleillement.service';
import { IPlante } from 'app/shared/model/plante.model';
import { PlanteService } from 'app/entities/plante';

@Component({
    selector: 'jhi-ensoleillement-update',
    templateUrl: './ensoleillement-update.component.html'
})
export class EnsoleillementUpdateComponent implements OnInit {
    ensoleillement: IEnsoleillement;
    isSaving: boolean;

    plantes: IPlante[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected ensoleillementService: EnsoleillementService,
        protected planteService: PlanteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ensoleillement }) => {
            this.ensoleillement = ensoleillement;
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
        if (this.ensoleillement.id !== undefined) {
            this.subscribeToSaveResponse(this.ensoleillementService.update(this.ensoleillement));
        } else {
            this.subscribeToSaveResponse(this.ensoleillementService.create(this.ensoleillement));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEnsoleillement>>) {
        result.subscribe((res: HttpResponse<IEnsoleillement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
