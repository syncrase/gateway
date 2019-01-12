import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Recolte } from 'app/shared/model/recolte.model';
import { RecolteService } from './recolte.service';
import { RecolteComponent } from './recolte.component';
import { RecolteDetailComponent } from './recolte-detail.component';
import { RecolteUpdateComponent } from './recolte-update.component';
import { RecolteDeletePopupComponent } from './recolte-delete-dialog.component';
import { IRecolte } from 'app/shared/model/recolte.model';

@Injectable({ providedIn: 'root' })
export class RecolteResolve implements Resolve<IRecolte> {
    constructor(private service: RecolteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Recolte> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Recolte>) => response.ok),
                map((recolte: HttpResponse<Recolte>) => recolte.body)
            );
        }
        return of(new Recolte());
    }
}

export const recolteRoute: Routes = [
    {
        path: 'recolte',
        component: RecolteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Recoltes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recolte/:id/view',
        component: RecolteDetailComponent,
        resolve: {
            recolte: RecolteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Recoltes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recolte/new',
        component: RecolteUpdateComponent,
        resolve: {
            recolte: RecolteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Recoltes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recolte/:id/edit',
        component: RecolteUpdateComponent,
        resolve: {
            recolte: RecolteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Recoltes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const recoltePopupRoute: Routes = [
    {
        path: 'recolte/:id/delete',
        component: RecolteDeletePopupComponent,
        resolve: {
            recolte: RecolteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Recoltes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
