import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    VitesseCroissanceComponent,
    VitesseCroissanceDetailComponent,
    VitesseCroissanceUpdateComponent,
    VitesseCroissanceDeletePopupComponent,
    VitesseCroissanceDeleteDialogComponent,
    vitesseCroissanceRoute,
    vitesseCroissancePopupRoute
} from './';

const ENTITY_STATES = [...vitesseCroissanceRoute, ...vitesseCroissancePopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VitesseCroissanceComponent,
        VitesseCroissanceDetailComponent,
        VitesseCroissanceUpdateComponent,
        VitesseCroissanceDeleteDialogComponent,
        VitesseCroissanceDeletePopupComponent
    ],
    entryComponents: [
        VitesseCroissanceComponent,
        VitesseCroissanceUpdateComponent,
        VitesseCroissanceDeleteDialogComponent,
        VitesseCroissanceDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayVitesseCroissanceModule {}
