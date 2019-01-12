import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeRacine } from 'app/shared/model/type-racine.model';

type EntityResponseType = HttpResponse<ITypeRacine>;
type EntityArrayResponseType = HttpResponse<ITypeRacine[]>;

@Injectable({ providedIn: 'root' })
export class TypeRacineService {
    public resourceUrl = SERVER_API_URL + 'api/type-racines';

    constructor(protected http: HttpClient) {}

    create(typeRacine: ITypeRacine): Observable<EntityResponseType> {
        return this.http.post<ITypeRacine>(this.resourceUrl, typeRacine, { observe: 'response' });
    }

    update(typeRacine: ITypeRacine): Observable<EntityResponseType> {
        return this.http.put<ITypeRacine>(this.resourceUrl, typeRacine, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITypeRacine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITypeRacine[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
