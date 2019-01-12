import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TypeRacine } from 'app/shared/model/type-racine.model';
import { TypeRacineService } from './type-racine.service';
import { TypeRacineComponent } from './type-racine.component';
import { TypeRacineDetailComponent } from './type-racine-detail.component';
import { TypeRacineUpdateComponent } from './type-racine-update.component';
import { TypeRacineDeletePopupComponent } from './type-racine-delete-dialog.component';
import { ITypeRacine } from 'app/shared/model/type-racine.model';

@Injectable({ providedIn: 'root' })
export class TypeRacineResolve implements Resolve<ITypeRacine> {
    constructor(private service: TypeRacineService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TypeRacine> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TypeRacine>) => response.ok),
                map((typeRacine: HttpResponse<TypeRacine>) => typeRacine.body)
            );
        }
        return of(new TypeRacine());
    }
}

export const typeRacineRoute: Routes = [
    {
        path: 'type-racine',
        component: TypeRacineComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeRacines'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-racine/:id/view',
        component: TypeRacineDetailComponent,
        resolve: {
            typeRacine: TypeRacineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeRacines'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-racine/new',
        component: TypeRacineUpdateComponent,
        resolve: {
            typeRacine: TypeRacineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeRacines'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-racine/:id/edit',
        component: TypeRacineUpdateComponent,
        resolve: {
            typeRacine: TypeRacineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeRacines'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeRacinePopupRoute: Routes = [
    {
        path: 'type-racine/:id/delete',
        component: TypeRacineDeletePopupComponent,
        resolve: {
            typeRacine: TypeRacineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeRacines'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
