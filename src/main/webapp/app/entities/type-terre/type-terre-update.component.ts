import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITypeTerre } from 'app/shared/model/type-terre.model';
import { TypeTerreService } from './type-terre.service';
import { IPlante } from 'app/shared/model/plante.model';
import { PlanteService } from 'app/entities/plante';

@Component({
    selector: 'jhi-type-terre-update',
    templateUrl: './type-terre-update.component.html'
})
export class TypeTerreUpdateComponent implements OnInit {
    typeTerre: ITypeTerre;
    isSaving: boolean;

    plantes: IPlante[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected typeTerreService: TypeTerreService,
        protected planteService: PlanteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeTerre }) => {
            this.typeTerre = typeTerre;
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
        if (this.typeTerre.id !== undefined) {
            this.subscribeToSaveResponse(this.typeTerreService.update(this.typeTerre));
        } else {
            this.subscribeToSaveResponse(this.typeTerreService.create(this.typeTerre));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeTerre>>) {
        result.subscribe((res: HttpResponse<ITypeTerre>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
