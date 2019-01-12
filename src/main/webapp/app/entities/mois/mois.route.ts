import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Mois } from 'app/shared/model/mois.model';
import { MoisService } from './mois.service';
import { MoisComponent } from './mois.component';
import { MoisDetailComponent } from './mois-detail.component';
import { MoisUpdateComponent } from './mois-update.component';
import { MoisDeletePopupComponent } from './mois-delete-dialog.component';
import { IMois } from 'app/shared/model/mois.model';

@Injectable({ providedIn: 'root' })
export class MoisResolve implements Resolve<IMois> {
    constructor(private service: MoisService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Mois> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Mois>) => response.ok),
                map((mois: HttpResponse<Mois>) => mois.body)
            );
        }
        return of(new Mois());
    }
}

export const moisRoute: Routes = [
    {
        path: 'mois',
        component: MoisComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mois'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mois/:id/view',
        component: MoisDetailComponent,
        resolve: {
            mois: MoisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mois'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mois/new',
        component: MoisUpdateComponent,
        resolve: {
            mois: MoisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mois'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mois/:id/edit',
        component: MoisUpdateComponent,
        resolve: {
            mois: MoisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mois'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const moisPopupRoute: Routes = [
    {
        path: 'mois/:id/delete',
        component: MoisDeletePopupComponent,
        resolve: {
            mois: MoisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mois'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
