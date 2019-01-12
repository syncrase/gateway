import { IPlante } from 'app/shared/model//plante.model';

export interface ITypeFeuillage {
    id?: number;
    typeFeuillage?: string;
    plante?: IPlante;
}

export class TypeFeuillage implements ITypeFeuillage {
    constructor(public id?: number, public typeFeuillage?: string, public plante?: IPlante) {}
}
