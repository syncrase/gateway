import { IPlante } from 'app/shared/model//plante.model';

export interface ITypeTerre {
    id?: number;
    typeTerre?: string;
    plante?: IPlante;
}

export class TypeTerre implements ITypeTerre {
    constructor(public id?: number, public typeTerre?: string, public plante?: IPlante) {}
}
