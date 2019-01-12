import { IPlante } from 'app/shared/model//plante.model';
import { IMois } from 'app/shared/model//mois.model';

export interface IFloraison {
    id?: number;
    plante?: IPlante;
    mois?: IMois;
}

export class Floraison implements IFloraison {
    constructor(public id?: number, public plante?: IPlante, public mois?: IMois) {}
}
