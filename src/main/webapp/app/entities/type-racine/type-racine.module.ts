import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    TypeRacineComponent,
    TypeRacineDetailComponent,
    TypeRacineUpdateComponent,
    TypeRacineDeletePopupComponent,
    TypeRacineDeleteDialogComponent,
    typeRacineRoute,
    typeRacinePopupRoute
} from './';

const ENTITY_STATES = [...typeRacineRoute, ...typeRacinePopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TypeRacineComponent,
        TypeRacineDetailComponent,
        TypeRacineUpdateComponent,
        TypeRacineDeleteDialogComponent,
        TypeRacineDeletePopupComponent
    ],
    entryComponents: [TypeRacineComponent, TypeRacineUpdateComponent, TypeRacineDeleteDialogComponent, TypeRacineDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayTypeRacineModule {}
