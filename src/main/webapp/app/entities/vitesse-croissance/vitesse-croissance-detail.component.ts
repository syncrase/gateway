import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVitesseCroissance } from 'app/shared/model/vitesse-croissance.model';

@Component({
    selector: 'jhi-vitesse-croissance-detail',
    templateUrl: './vitesse-croissance-detail.component.html'
})
export class VitesseCroissanceDetailComponent implements OnInit {
    vitesseCroissance: IVitesseCroissance;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vitesseCroissance }) => {
            this.vitesseCroissance = vitesseCroissance;
        });
    }

    previousState() {
        window.history.back();
    }
}
