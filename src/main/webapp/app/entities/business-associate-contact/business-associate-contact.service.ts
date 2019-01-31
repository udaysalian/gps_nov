import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBusinessAssociateContact } from 'app/shared/model/business-associate-contact.model';

type EntityResponseType = HttpResponse<IBusinessAssociateContact>;
type EntityArrayResponseType = HttpResponse<IBusinessAssociateContact[]>;

@Injectable({ providedIn: 'root' })
export class BusinessAssociateContactService {
    private resourceUrl = SERVER_API_URL + 'api/business-associate-contacts';

    constructor(private http: HttpClient) {}

    create(businessAssociateContact: IBusinessAssociateContact): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(businessAssociateContact);
        return this.http
            .post<IBusinessAssociateContact>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(businessAssociateContact: IBusinessAssociateContact): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(businessAssociateContact);
        return this.http
            .put<IBusinessAssociateContact>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBusinessAssociateContact>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBusinessAssociateContact[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(businessAssociateContact: IBusinessAssociateContact): IBusinessAssociateContact {
        const copy: IBusinessAssociateContact = Object.assign({}, businessAssociateContact, {
            beginDate:
                businessAssociateContact.beginDate != null && businessAssociateContact.beginDate.isValid()
                    ? businessAssociateContact.beginDate.format(DATE_FORMAT)
                    : null,
            endDate:
                businessAssociateContact.endDate != null && businessAssociateContact.endDate.isValid()
                    ? businessAssociateContact.endDate.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.beginDate = res.body.beginDate != null ? moment(res.body.beginDate) : null;
        res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((businessAssociateContact: IBusinessAssociateContact) => {
            businessAssociateContact.beginDate =
                businessAssociateContact.beginDate != null ? moment(businessAssociateContact.beginDate) : null;
            businessAssociateContact.endDate = businessAssociateContact.endDate != null ? moment(businessAssociateContact.endDate) : null;
        });
        return res;
    }
}
