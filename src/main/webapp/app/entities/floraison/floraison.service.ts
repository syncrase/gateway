import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFloraison } from 'app/shared/model/floraison.model';

type EntityResponseType = HttpResponse<IFloraison>;
type EntityArrayResponseType = HttpResponse<IFloraison[]>;

@Injectable({ providedIn: 'root' })
export class FloraisonService {
    public resourceUrl = SERVER_API_URL + 'api/floraisons';

    constructor(protected http: HttpClient) {}

    create(floraison: IFloraison): Observable<EntityResponseType> {
        return this.http.post<IFloraison>(this.resourceUrl, floraison, { observe: 'response' });
    }

    update(floraison: IFloraison): Observable<EntityResponseType> {
        return this.http.put<IFloraison>(this.resourceUrl, floraison, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFloraison>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFloraison[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
