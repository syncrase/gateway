import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnsoleillement } from 'app/shared/model/ensoleillement.model';

@Component({
    selector: 'jhi-ensoleillement-detail',
    templateUrl: './ensoleillement-detail.component.html'
})
export class EnsoleillementDetailComponent implements OnInit {
    ensoleillement: IEnsoleillement;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ensoleillement }) => {
            this.ensoleillement = ensoleillement;
        });
    }

    previousState() {
        window.history.back();
    }
}
