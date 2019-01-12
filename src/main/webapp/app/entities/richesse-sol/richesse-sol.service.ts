import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRichesseSol } from 'app/shared/model/richesse-sol.model';

type EntityResponseType = HttpResponse<IRichesseSol>;
type EntityArrayResponseType = HttpResponse<IRichesseSol[]>;

@Injectable({ providedIn: 'root' })
export class RichesseSolService {
    public resourceUrl = SERVER_API_URL + 'api/richesse-sols';

    constructor(protected http: HttpClient) {}

    create(richesseSol: IRichesseSol): Observable<EntityResponseType> {
        return this.http.post<IRichesseSol>(this.resourceUrl, richesseSol, { observe: 'response' });
    }

    update(richesseSol: IRichesseSol): Observable<EntityResponseType> {
        return this.http.put<IRichesseSol>(this.resourceUrl, richesseSol, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRichesseSol>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRichesseSol[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
