import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    FloraisonComponent,
    FloraisonDetailComponent,
    FloraisonUpdateComponent,
    FloraisonDeletePopupComponent,
    FloraisonDeleteDialogComponent,
    floraisonRoute,
    floraisonPopupRoute
} from './';

const ENTITY_STATES = [...floraisonRoute, ...floraisonPopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FloraisonComponent,
        FloraisonDetailComponent,
        FloraisonUpdateComponent,
        FloraisonDeleteDialogComponent,
        FloraisonDeletePopupComponent
    ],
    entryComponents: [FloraisonComponent, FloraisonUpdateComponent, FloraisonDeleteDialogComponent, FloraisonDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayFloraisonModule {}
