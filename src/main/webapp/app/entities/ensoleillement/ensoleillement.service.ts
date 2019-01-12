import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnsoleillement } from 'app/shared/model/ensoleillement.model';

type EntityResponseType = HttpResponse<IEnsoleillement>;
type EntityArrayResponseType = HttpResponse<IEnsoleillement[]>;

@Injectable({ providedIn: 'root' })
export class EnsoleillementService {
    public resourceUrl = SERVER_API_URL + 'api/ensoleillements';

    constructor(protected http: HttpClient) {}

    create(ensoleillement: IEnsoleillement): Observable<EntityResponseType> {
        return this.http.post<IEnsoleillement>(this.resourceUrl, ensoleillement, { observe: 'response' });
    }

    update(ensoleillement: IEnsoleillement): Observable<EntityResponseType> {
        return this.http.put<IEnsoleillement>(this.resourceUrl, ensoleillement, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnsoleillement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnsoleillement[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
