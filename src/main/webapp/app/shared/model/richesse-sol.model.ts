import { IPlante } from 'app/shared/model//plante.model';

export interface IRichesseSol {
    id?: number;
    richesseSol?: string;
    plante?: IPlante;
}

export class RichesseSol implements IRichesseSol {
    constructor(public id?: number, public richesseSol?: string, public plante?: IPlante) {}
}
