import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeFeuillage } from 'app/shared/model/type-feuillage.model';

type EntityResponseType = HttpResponse<ITypeFeuillage>;
type EntityArrayResponseType = HttpResponse<ITypeFeuillage[]>;

@Injectable({ providedIn: 'root' })
export class TypeFeuillageService {
    public resourceUrl = SERVER_API_URL + 'api/type-feuillages';

    constructor(protected http: HttpClient) {}

    create(typeFeuillage: ITypeFeuillage): Observable<EntityResponseType> {
        return this.http.post<ITypeFeuillage>(this.resourceUrl, typeFeuillage, { observe: 'response' });
    }

    update(typeFeuillage: ITypeFeuillage): Observable<EntityResponseType> {
        return this.http.put<ITypeFeuillage>(this.resourceUrl, typeFeuillage, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITypeFeuillage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITypeFeuillage[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
