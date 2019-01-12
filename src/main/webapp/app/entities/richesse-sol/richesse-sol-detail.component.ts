import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRichesseSol } from 'app/shared/model/richesse-sol.model';

@Component({
    selector: 'jhi-richesse-sol-detail',
    templateUrl: './richesse-sol-detail.component.html'
})
export class RichesseSolDetailComponent implements OnInit {
    richesseSol: IRichesseSol;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ richesseSol }) => {
            this.richesseSol = richesseSol;
        });
    }

    previousState() {
        window.history.back();
    }
}
