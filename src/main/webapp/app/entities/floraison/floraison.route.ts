import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Floraison } from 'app/shared/model/floraison.model';
import { FloraisonService } from './floraison.service';
import { FloraisonComponent } from './floraison.component';
import { FloraisonDetailComponent } from './floraison-detail.component';
import { FloraisonUpdateComponent } from './floraison-update.component';
import { FloraisonDeletePopupComponent } from './floraison-delete-dialog.component';
import { IFloraison } from 'app/shared/model/floraison.model';

@Injectable({ providedIn: 'root' })
export class FloraisonResolve implements Resolve<IFloraison> {
    constructor(private service: FloraisonService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Floraison> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Floraison>) => response.ok),
                map((floraison: HttpResponse<Floraison>) => floraison.body)
            );
        }
        return of(new Floraison());
    }
}

export const floraisonRoute: Routes = [
    {
        path: 'floraison',
        component: FloraisonComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Floraisons'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'floraison/:id/view',
        component: FloraisonDetailComponent,
        resolve: {
            floraison: FloraisonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Floraisons'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'floraison/new',
        component: FloraisonUpdateComponent,
        resolve: {
            floraison: FloraisonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Floraisons'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'floraison/:id/edit',
        component: FloraisonUpdateComponent,
        resolve: {
            floraison: FloraisonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Floraisons'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const floraisonPopupRoute: Routes = [
    {
        path: 'floraison/:id/delete',
        component: FloraisonDeletePopupComponent,
        resolve: {
            floraison: FloraisonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Floraisons'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
