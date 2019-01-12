import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeFeuillage } from 'app/shared/model/type-feuillage.model';

@Component({
    selector: 'jhi-type-feuillage-detail',
    templateUrl: './type-feuillage-detail.component.html'
})
export class TypeFeuillageDetailComponent implements OnInit {
    typeFeuillage: ITypeFeuillage;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeFeuillage }) => {
            this.typeFeuillage = typeFeuillage;
        });
    }

    previousState() {
        window.history.back();
    }
}
