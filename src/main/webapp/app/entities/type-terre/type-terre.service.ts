import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeTerre } from 'app/shared/model/type-terre.model';

type EntityResponseType = HttpResponse<ITypeTerre>;
type EntityArrayResponseType = HttpResponse<ITypeTerre[]>;

@Injectable({ providedIn: 'root' })
export class TypeTerreService {
    public resourceUrl = SERVER_API_URL + 'api/type-terres';

    constructor(protected http: HttpClient) {}

    create(typeTerre: ITypeTerre): Observable<EntityResponseType> {
        return this.http.post<ITypeTerre>(this.resourceUrl, typeTerre, { observe: 'response' });
    }

    update(typeTerre: ITypeTerre): Observable<EntityResponseType> {
        return this.http.put<ITypeTerre>(this.resourceUrl, typeTerre, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITypeTerre>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITypeTerre[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
