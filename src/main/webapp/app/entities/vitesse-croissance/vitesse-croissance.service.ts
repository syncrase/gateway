import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVitesseCroissance } from 'app/shared/model/vitesse-croissance.model';

type EntityResponseType = HttpResponse<IVitesseCroissance>;
type EntityArrayResponseType = HttpResponse<IVitesseCroissance[]>;

@Injectable({ providedIn: 'root' })
export class VitesseCroissanceService {
    public resourceUrl = SERVER_API_URL + 'api/vitesse-croissances';

    constructor(protected http: HttpClient) {}

    create(vitesseCroissance: IVitesseCroissance): Observable<EntityResponseType> {
        return this.http.post<IVitesseCroissance>(this.resourceUrl, vitesseCroissance, { observe: 'response' });
    }

    update(vitesseCroissance: IVitesseCroissance): Observable<EntityResponseType> {
        return this.http.put<IVitesseCroissance>(this.resourceUrl, vitesseCroissance, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IVitesseCroissance>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IVitesseCroissance[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
