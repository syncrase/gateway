import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TypeTerre } from 'app/shared/model/type-terre.model';
import { TypeTerreService } from './type-terre.service';
import { TypeTerreComponent } from './type-terre.component';
import { TypeTerreDetailComponent } from './type-terre-detail.component';
import { TypeTerreUpdateComponent } from './type-terre-update.component';
import { TypeTerreDeletePopupComponent } from './type-terre-delete-dialog.component';
import { ITypeTerre } from 'app/shared/model/type-terre.model';

@Injectable({ providedIn: 'root' })
export class TypeTerreResolve implements Resolve<ITypeTerre> {
    constructor(private service: TypeTerreService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TypeTerre> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TypeTerre>) => response.ok),
                map((typeTerre: HttpResponse<TypeTerre>) => typeTerre.body)
            );
        }
        return of(new TypeTerre());
    }
}

export const typeTerreRoute: Routes = [
    {
        path: 'type-terre',
        component: TypeTerreComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeTerres'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-terre/:id/view',
        component: TypeTerreDetailComponent,
        resolve: {
            typeTerre: TypeTerreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeTerres'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-terre/new',
        component: TypeTerreUpdateComponent,
        resolve: {
            typeTerre: TypeTerreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeTerres'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-terre/:id/edit',
        component: TypeTerreUpdateComponent,
        resolve: {
            typeTerre: TypeTerreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeTerres'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeTerrePopupRoute: Routes = [
    {
        path: 'type-terre/:id/delete',
        component: TypeTerreDeletePopupComponent,
        resolve: {
            typeTerre: TypeTerreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeTerres'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
