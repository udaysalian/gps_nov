///<reference path="../../../../../../node_modules/@angular/common/http/src/client.d.ts"/>
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INomination } from 'app/shared/model/nomination.model';

type EntityResponseType = HttpResponse<INomination>;
type EntityArrayResponseType = HttpResponse<INomination[]>;

@Injectable({ providedIn: 'root' })
export class NominationService {
    private resourceUrl = SERVER_API_URL + 'api/nominations';

    constructor(private http: HttpClient) {}

    create(nomination: INomination): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nomination);
        return this.http
            .post<INomination>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(nomination: INomination): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nomination);
        return this.http
            .put<INomination>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INomination>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INomination[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(nomination: INomination): INomination {
        const copy: INomination = Object.assign({}, nomination, {
            gasDate: nomination.gasDate != null && nomination.gasDate.isValid() ? nomination.gasDate.format(DATE_FORMAT) : null,
            updateTimeStamp:
                nomination.updateTimeStamp != null && nomination.updateTimeStamp.isValid() ? nomination.updateTimeStamp.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.gasDate = res.body.gasDate != null ? moment(res.body.gasDate) : null;
        res.body.updateTimeStamp = res.body.updateTimeStamp != null ? moment(res.body.updateTimeStamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((nomination: INomination) => {
            nomination.gasDate = nomination.gasDate != null ? moment(nomination.gasDate) : null;
            nomination.updateTimeStamp = nomination.updateTimeStamp != null ? moment(nomination.updateTimeStamp) : null;
        });
        return res;
    }
}
