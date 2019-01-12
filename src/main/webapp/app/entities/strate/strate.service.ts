import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStrate } from 'app/shared/model/strate.model';

type EntityResponseType = HttpResponse<IStrate>;
type EntityArrayResponseType = HttpResponse<IStrate[]>;

@Injectable({ providedIn: 'root' })
export class StrateService {
    public resourceUrl = SERVER_API_URL + 'api/strates';

    constructor(protected http: HttpClient) {}

    create(strate: IStrate): Observable<EntityResponseType> {
        return this.http.post<IStrate>(this.resourceUrl, strate, { observe: 'response' });
    }

    update(strate: IStrate): Observable<EntityResponseType> {
        return this.http.put<IStrate>(this.resourceUrl, strate, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IStrate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStrate[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
