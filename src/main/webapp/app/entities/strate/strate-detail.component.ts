import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStrate } from 'app/shared/model/strate.model';

@Component({
    selector: 'jhi-strate-detail',
    templateUrl: './strate-detail.component.html'
})
export class StrateDetailComponent implements OnInit {
    strate: IStrate;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ strate }) => {
            this.strate = strate;
        });
    }

    previousState() {
        window.history.back();
    }
}
