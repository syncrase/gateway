import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TypeFeuillage } from 'app/shared/model/type-feuillage.model';
import { TypeFeuillageService } from './type-feuillage.service';
import { TypeFeuillageComponent } from './type-feuillage.component';
import { TypeFeuillageDetailComponent } from './type-feuillage-detail.component';
import { TypeFeuillageUpdateComponent } from './type-feuillage-update.component';
import { TypeFeuillageDeletePopupComponent } from './type-feuillage-delete-dialog.component';
import { ITypeFeuillage } from 'app/shared/model/type-feuillage.model';

@Injectable({ providedIn: 'root' })
export class TypeFeuillageResolve implements Resolve<ITypeFeuillage> {
    constructor(private service: TypeFeuillageService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TypeFeuillage> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TypeFeuillage>) => response.ok),
                map((typeFeuillage: HttpResponse<TypeFeuillage>) => typeFeuillage.body)
            );
        }
        return of(new TypeFeuillage());
    }
}

export const typeFeuillageRoute: Routes = [
    {
        path: 'type-feuillage',
        component: TypeFeuillageComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeFeuillages'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-feuillage/:id/view',
        component: TypeFeuillageDetailComponent,
        resolve: {
            typeFeuillage: TypeFeuillageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeFeuillages'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-feuillage/new',
        component: TypeFeuillageUpdateComponent,
        resolve: {
            typeFeuillage: TypeFeuillageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeFeuillages'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-feuillage/:id/edit',
        component: TypeFeuillageUpdateComponent,
        resolve: {
            typeFeuillage: TypeFeuillageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeFeuillages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeFeuillagePopupRoute: Routes = [
    {
        path: 'type-feuillage/:id/delete',
        component: TypeFeuillageDeletePopupComponent,
        resolve: {
            typeFeuillage: TypeFeuillageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeFeuillages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
