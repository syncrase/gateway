import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RichesseSol } from 'app/shared/model/richesse-sol.model';
import { RichesseSolService } from './richesse-sol.service';
import { RichesseSolComponent } from './richesse-sol.component';
import { RichesseSolDetailComponent } from './richesse-sol-detail.component';
import { RichesseSolUpdateComponent } from './richesse-sol-update.component';
import { RichesseSolDeletePopupComponent } from './richesse-sol-delete-dialog.component';
import { IRichesseSol } from 'app/shared/model/richesse-sol.model';

@Injectable({ providedIn: 'root' })
export class RichesseSolResolve implements Resolve<IRichesseSol> {
    constructor(private service: RichesseSolService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RichesseSol> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RichesseSol>) => response.ok),
                map((richesseSol: HttpResponse<RichesseSol>) => richesseSol.body)
            );
        }
        return of(new RichesseSol());
    }
}

export const richesseSolRoute: Routes = [
    {
        path: 'richesse-sol',
        component: RichesseSolComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RichesseSols'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'richesse-sol/:id/view',
        component: RichesseSolDetailComponent,
        resolve: {
            richesseSol: RichesseSolResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RichesseSols'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'richesse-sol/new',
        component: RichesseSolUpdateComponent,
        resolve: {
            richesseSol: RichesseSolResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RichesseSols'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'richesse-sol/:id/edit',
        component: RichesseSolUpdateComponent,
        resolve: {
            richesseSol: RichesseSolResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RichesseSols'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const richesseSolPopupRoute: Routes = [
    {
        path: 'richesse-sol/:id/delete',
        component: RichesseSolDeletePopupComponent,
        resolve: {
            richesseSol: RichesseSolResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RichesseSols'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
