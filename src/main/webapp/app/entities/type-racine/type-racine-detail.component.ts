import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeRacine } from 'app/shared/model/type-racine.model';

@Component({
    selector: 'jhi-type-racine-detail',
    templateUrl: './type-racine-detail.component.html'
})
export class TypeRacineDetailComponent implements OnInit {
    typeRacine: ITypeRacine;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeRacine }) => {
            this.typeRacine = typeRacine;
        });
    }

    previousState() {
        window.history.back();
    }
}
