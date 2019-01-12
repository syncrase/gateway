import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeTerre } from 'app/shared/model/type-terre.model';

@Component({
    selector: 'jhi-type-terre-detail',
    templateUrl: './type-terre-detail.component.html'
})
export class TypeTerreDetailComponent implements OnInit {
    typeTerre: ITypeTerre;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeTerre }) => {
            this.typeTerre = typeTerre;
        });
    }

    previousState() {
        window.history.back();
    }
}
