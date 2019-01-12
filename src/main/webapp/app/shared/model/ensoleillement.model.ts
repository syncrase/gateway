import { IPlante } from 'app/shared/model//plante.model';

export interface IEnsoleillement {
    id?: number;
    ensoleillement?: string;
    plante?: IPlante;
}

export class Ensoleillement implements IEnsoleillement {
    constructor(public id?: number, public ensoleillement?: string, public plante?: IPlante) {}
}
