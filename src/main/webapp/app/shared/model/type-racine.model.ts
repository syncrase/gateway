import { IPlante } from 'app/shared/model//plante.model';

export interface ITypeRacine {
    id?: number;
    typeRacine?: string;
    plante?: IPlante;
}

export class TypeRacine implements ITypeRacine {
    constructor(public id?: number, public typeRacine?: string, public plante?: IPlante) {}
}
