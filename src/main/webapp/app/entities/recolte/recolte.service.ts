import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRecolte } from 'app/shared/model/recolte.model';

type EntityResponseType = HttpResponse<IRecolte>;
type EntityArrayResponseType = HttpResponse<IRecolte[]>;

@Injectable({ providedIn: 'root' })
export class RecolteService {
    public resourceUrl = SERVER_API_URL + 'api/recoltes';

    constructor(protected http: HttpClient) {}

    create(recolte: IRecolte): Observable<EntityResponseType> {
        return this.http.post<IRecolte>(this.resourceUrl, recolte, { observe: 'response' });
    }

    update(recolte: IRecolte): Observable<EntityResponseType> {
        return this.http.put<IRecolte>(this.resourceUrl, recolte, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRecolte>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRecolte[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
