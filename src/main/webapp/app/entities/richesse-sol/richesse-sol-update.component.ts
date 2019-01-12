import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IRichesseSol } from 'app/shared/model/richesse-sol.model';
import { RichesseSolService } from './richesse-sol.service';
import { IPlante } from 'app/shared/model/plante.model';
import { PlanteService } from 'app/entities/plante';

@Component({
    selector: 'jhi-richesse-sol-update',
    templateUrl: './richesse-sol-update.component.html'
})
export class RichesseSolUpdateComponent implements OnInit {
    richesseSol: IRichesseSol;
    isSaving: boolean;

    plantes: IPlante[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected richesseSolService: RichesseSolService,
        protected planteService: PlanteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ richesseSol }) => {
            this.richesseSol = richesseSol;
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
        if (this.richesseSol.id !== undefined) {
            this.subscribeToSaveResponse(this.richesseSolService.update(this.richesseSol));
        } else {
            this.subscribeToSaveResponse(this.richesseSolService.create(this.richesseSol));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRichesseSol>>) {
        result.subscribe((res: HttpResponse<IRichesseSol>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
