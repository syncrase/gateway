import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Strate } from 'app/shared/model/strate.model';
import { StrateService } from './strate.service';
import { StrateComponent } from './strate.component';
import { StrateDetailComponent } from './strate-detail.component';
import { StrateUpdateComponent } from './strate-update.component';
import { StrateDeletePopupComponent } from './strate-delete-dialog.component';
import { IStrate } from 'app/shared/model/strate.model';

@Injectable({ providedIn: 'root' })
export class StrateResolve implements Resolve<IStrate> {
    constructor(private service: StrateService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Strate> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Strate>) => response.ok),
                map((strate: HttpResponse<Strate>) => strate.body)
            );
        }
        return of(new Strate());
    }
}

export const strateRoute: Routes = [
    {
        path: 'strate',
        component: StrateComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Strates'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'strate/:id/view',
        component: StrateDetailComponent,
        resolve: {
            strate: StrateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Strates'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'strate/new',
        component: StrateUpdateComponent,
        resolve: {
            strate: StrateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Strates'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'strate/:id/edit',
        component: StrateUpdateComponent,
        resolve: {
            strate: StrateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Strates'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stratePopupRoute: Routes = [
    {
        path: 'strate/:id/delete',
        component: StrateDeletePopupComponent,
        resolve: {
            strate: StrateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Strates'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
