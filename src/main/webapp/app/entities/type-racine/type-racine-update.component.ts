import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITypeRacine } from 'app/shared/model/type-racine.model';
import { TypeRacineService } from './type-racine.service';
import { IPlante } from 'app/shared/model/plante.model';
import { PlanteService } from 'app/entities/plante';

@Component({
    selector: 'jhi-type-racine-update',
    templateUrl: './type-racine-update.component.html'
})
export class TypeRacineUpdateComponent implements OnInit {
    typeRacine: ITypeRacine;
    isSaving: boolean;

    plantes: IPlante[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected typeRacineService: TypeRacineService,
        protected planteService: PlanteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeRacine }) => {
            this.typeRacine = typeRacine;
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
        if (this.typeRacine.id !== undefined) {
            this.subscribeToSaveResponse(this.typeRacineService.update(this.typeRacine));
        } else {
            this.subscribeToSaveResponse(this.typeRacineService.create(this.typeRacine));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeRacine>>) {
        result.subscribe((res: HttpResponse<ITypeRacine>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
