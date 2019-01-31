import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBusinessAssociate } from 'app/shared/model/business-associate.model';

type EntityResponseType = HttpResponse<IBusinessAssociate>;
type EntityArrayResponseType = HttpResponse<IBusinessAssociate[]>;

@Injectable({ providedIn: 'root' })
export class BusinessAssociateService {
    private resourceUrl = SERVER_API_URL + 'api/business-associates';

    constructor(private http: HttpClient) {}

    create(businessAssociate: IBusinessAssociate): Observable<EntityResponseType> {
        return this.http.post<IBusinessAssociate>(this.resourceUrl, businessAssociate, { observe: 'response' });
    }

    update(businessAssociate: IBusinessAssociate): Observable<EntityResponseType> {
        return this.http.put<IBusinessAssociate>(this.resourceUrl, businessAssociate, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBusinessAssociate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBusinessAssociate[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
