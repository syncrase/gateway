import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VitesseCroissance } from 'app/shared/model/vitesse-croissance.model';
import { VitesseCroissanceService } from './vitesse-croissance.service';
import { VitesseCroissanceComponent } from './vitesse-croissance.component';
import { VitesseCroissanceDetailComponent } from './vitesse-croissance-detail.component';
import { VitesseCroissanceUpdateComponent } from './vitesse-croissance-update.component';
import { VitesseCroissanceDeletePopupComponent } from './vitesse-croissance-delete-dialog.component';
import { IVitesseCroissance } from 'app/shared/model/vitesse-croissance.model';

@Injectable({ providedIn: 'root' })
export class VitesseCroissanceResolve implements Resolve<IVitesseCroissance> {
    constructor(private service: VitesseCroissanceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VitesseCroissance> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<VitesseCroissance>) => response.ok),
                map((vitesseCroissance: HttpResponse<VitesseCroissance>) => vitesseCroissance.body)
            );
        }
        return of(new VitesseCroissance());
    }
}

export const vitesseCroissanceRoute: Routes = [
    {
        path: 'vitesse-croissance',
        component: VitesseCroissanceComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VitesseCroissances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vitesse-croissance/:id/view',
        component: VitesseCroissanceDetailComponent,
        resolve: {
            vitesseCroissance: VitesseCroissanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VitesseCroissances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vitesse-croissance/new',
        component: VitesseCroissanceUpdateComponent,
        resolve: {
            vitesseCroissance: VitesseCroissanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VitesseCroissances'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vitesse-croissance/:id/edit',
        component: VitesseCroissanceUpdateComponent,
        resolve: {
            vitesseCroissance: VitesseCroissanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VitesseCroissances'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vitesseCroissancePopupRoute: Routes = [
    {
        path: 'vitesse-croissance/:id/delete',
        component: VitesseCroissanceDeletePopupComponent,
        resolve: {
            vitesseCroissance: VitesseCroissanceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VitesseCroissances'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
