import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFloraison } from 'app/shared/model/floraison.model';

@Component({
    selector: 'jhi-floraison-detail',
    templateUrl: './floraison-detail.component.html'
})
export class FloraisonDetailComponent implements OnInit {
    floraison: IFloraison;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ floraison }) => {
            this.floraison = floraison;
        });
    }

    previousState() {
        window.history.back();
    }
}
