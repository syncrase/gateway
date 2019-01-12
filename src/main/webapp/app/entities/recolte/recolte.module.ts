import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    RecolteComponent,
    RecolteDetailComponent,
    RecolteUpdateComponent,
    RecolteDeletePopupComponent,
    RecolteDeleteDialogComponent,
    recolteRoute,
    recoltePopupRoute
} from './';

const ENTITY_STATES = [...recolteRoute, ...recoltePopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RecolteComponent,
        RecolteDetailComponent,
        RecolteUpdateComponent,
        RecolteDeleteDialogComponent,
        RecolteDeletePopupComponent
    ],
    entryComponents: [RecolteComponent, RecolteUpdateComponent, RecolteDeleteDialogComponent, RecolteDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayRecolteModule {}
