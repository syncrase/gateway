import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GatewayPlanteModule } from './plante/plante.module';
import { GatewayRecolteModule } from './recolte/recolte.module';
import { GatewayFloraisonModule } from './floraison/floraison.module';
import { GatewayClassificationCronquistModule } from './classification-cronquist/classification-cronquist.module';
import { GatewayOrdreModule } from './ordre/ordre.module';
import { GatewayFamilleModule } from './famille/famille.module';
import { GatewayGenreModule } from './genre/genre.module';
import { GatewayEspeceModule } from './espece/espece.module';
import { GatewayInteractionPlantePlanteModule } from './interaction-plante-plante/interaction-plante-plante.module';
import { GatewayStrateModule } from './strate/strate.module';
import { GatewayVitesseCroissanceModule } from './vitesse-croissance/vitesse-croissance.module';
import { GatewayEnsoleillementModule } from './ensoleillement/ensoleillement.module';
import { GatewayRichesseSolModule } from './richesse-sol/richesse-sol.module';
import { GatewayTypeTerreModule } from './type-terre/type-terre.module';
import { GatewayTypeFeuillageModule } from './type-feuillage/type-feuillage.module';
import { GatewayTypeRacineModule } from './type-racine/type-racine.module';
import { GatewayMoisModule } from './mois/mois.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        GatewayPlanteModule,
        GatewayRecolteModule,
        GatewayFloraisonModule,
        GatewayClassificationCronquistModule,
        GatewayOrdreModule,
        GatewayFamilleModule,
        GatewayGenreModule,
        GatewayEspeceModule,
        GatewayInteractionPlantePlanteModule,
        GatewayStrateModule,
        GatewayVitesseCroissanceModule,
        GatewayEnsoleillementModule,
        GatewayRichesseSolModule,
        GatewayTypeTerreModule,
        GatewayTypeFeuillageModule,
        GatewayTypeRacineModule,
        GatewayMoisModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayEntityModule {}
