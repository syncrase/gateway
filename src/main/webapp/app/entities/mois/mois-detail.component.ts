import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMois } from 'app/shared/model/mois.model';

@Component({
    selector: 'jhi-mois-detail',
    templateUrl: './mois-detail.component.html'
})
export class MoisDetailComponent implements OnInit {
    mois: IMois;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mois }) => {
            this.mois = mois;
        });
    }

    previousState() {
        window.history.back();
    }
}
