import { IClassificationCronquist } from 'app/shared/model//classification-cronquist.model';
import { IStrate } from 'app/shared/model//strate.model';
import { IVitesseCroissance } from 'app/shared/model//vitesse-croissance.model';
import { IEnsoleillement } from 'app/shared/model//ensoleillement.model';
import { IRichesseSol } from 'app/shared/model//richesse-sol.model';
import { ITypeTerre } from 'app/shared/model//type-terre.model';
import { ITypeFeuillage } from 'app/shared/model//type-feuillage.model';
import { ITypeRacine } from 'app/shared/model//type-racine.model';
import { IRecolte } from 'app/shared/model//recolte.model';
import { IFloraison } from 'app/shared/model//floraison.model';

export interface IPlante {
    id?: number;
    phMin?: string;
    phMax?: string;
    tempMin?: number;
    tempMax?: number;
    classificationCronquist?: IClassificationCronquist;
    strate?: IStrate;
    vitesseCroissance?: IVitesseCroissance;
    ensoleillement?: IEnsoleillement;
    richesseSol?: IRichesseSol;
    typeTerre?: ITypeTerre;
    typeFeuillage?: ITypeFeuillage;
    typeRacine?: ITypeRacine;
    recolte?: IRecolte;
    floraison?: IFloraison;
}

export class Plante implements IPlante {
    constructor(
        public id?: number,
        public phMin?: string,
        public phMax?: string,
        public tempMin?: number,
        public tempMax?: number,
        public classificationCronquist?: IClassificationCronquist,
        public strate?: IStrate,
        public vitesseCroissance?: IVitesseCroissance,
        public ensoleillement?: IEnsoleillement,
        public richesseSol?: IRichesseSol,
        public typeTerre?: ITypeTerre,
        public typeFeuillage?: ITypeFeuillage,
        public typeRacine?: ITypeRacine,
        public recolte?: IRecolte,
        public floraison?: IFloraison
    ) {}
}
