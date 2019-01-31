import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBusinessAssociateAddress } from 'app/shared/model/business-associate-address.model';

type EntityResponseType = HttpResponse<IBusinessAssociateAddress>;
type EntityArrayResponseType = HttpResponse<IBusinessAssociateAddress[]>;

@Injectable({ providedIn: 'root' })
export class BusinessAssociateAddressService {
    private resourceUrl = SERVER_API_URL + 'api/business-associate-addresses';

    constructor(private http: HttpClient) {}

    create(businessAssociateAddress: IBusinessAssociateAddress): Observable<EntityResponseType> {
        return this.http.post<IBusinessAssociateAddress>(this.resourceUrl, businessAssociateAddress, { observe: 'response' });
    }

    update(businessAssociateAddress: IBusinessAssociateAddress): Observable<EntityResponseType> {
        return this.http.put<IBusinessAssociateAddress>(this.resourceUrl, businessAssociateAddress, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBusinessAssociateAddress>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBusinessAssociateAddress[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
