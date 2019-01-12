import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRecolte } from 'app/shared/model/recolte.model';

@Component({
    selector: 'jhi-recolte-detail',
    templateUrl: './recolte-detail.component.html'
})
export class RecolteDetailComponent implements OnInit {
    recolte: IRecolte;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ recolte }) => {
            this.recolte = recolte;
        });
    }

    previousState() {
        window.history.back();
    }
}
