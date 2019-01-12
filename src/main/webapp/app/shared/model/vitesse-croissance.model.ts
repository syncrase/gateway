import { IPlante } from 'app/shared/model//plante.model';

export interface IVitesseCroissance {
    id?: number;
    vitesseCroissance?: string;
    plante?: IPlante;
}

export class VitesseCroissance implements IVitesseCroissance {
    constructor(public id?: number, public vitesseCroissance?: string, public plante?: IPlante) {}
}
