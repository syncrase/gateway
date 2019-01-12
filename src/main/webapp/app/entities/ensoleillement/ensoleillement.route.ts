import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Ensoleillement } from 'app/shared/model/ensoleillement.model';
import { EnsoleillementService } from './ensoleillement.service';
import { EnsoleillementComponent } from './ensoleillement.component';
import { EnsoleillementDetailComponent } from './ensoleillement-detail.component';
import { EnsoleillementUpdateComponent } from './ensoleillement-update.component';
import { EnsoleillementDeletePopupComponent } from './ensoleillement-delete-dialog.component';
import { IEnsoleillement } from 'app/shared/model/ensoleillement.model';

@Injectable({ providedIn: 'root' })
export class EnsoleillementResolve implements Resolve<IEnsoleillement> {
    constructor(private service: EnsoleillementService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Ensoleillement> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Ensoleillement>) => response.ok),
                map((ensoleillement: HttpResponse<Ensoleillement>) => ensoleillement.body)
            );
        }
        return of(new Ensoleillement());
    }
}

export const ensoleillementRoute: Routes = [
    {
        path: 'ensoleillement',
        component: EnsoleillementComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ensoleillements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ensoleillement/:id/view',
        component: EnsoleillementDetailComponent,
        resolve: {
            ensoleillement: EnsoleillementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ensoleillements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ensoleillement/new',
        component: EnsoleillementUpdateComponent,
        resolve: {
            ensoleillement: EnsoleillementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ensoleillements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ensoleillement/:id/edit',
        component: EnsoleillementUpdateComponent,
        resolve: {
            ensoleillement: EnsoleillementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ensoleillements'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ensoleillementPopupRoute: Routes = [
    {
        path: 'ensoleillement/:id/delete',
        component: EnsoleillementDeletePopupComponent,
        resolve: {
            ensoleillement: EnsoleillementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ensoleillements'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
