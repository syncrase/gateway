import { IPlante } from 'app/shared/model//plante.model';

export interface IStrate {
    id?: number;
    strate?: string;
    plante?: IPlante;
}

export class Strate implements IStrate {
    constructor(public id?: number, public strate?: string, public plante?: IPlante) {}
}
