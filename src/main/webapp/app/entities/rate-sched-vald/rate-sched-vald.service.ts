import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRateSchedVald } from 'app/shared/model/rate-sched-vald.model';

type EntityResponseType = HttpResponse<IRateSchedVald>;
type EntityArrayResponseType = HttpResponse<IRateSchedVald[]>;

@Injectable({ providedIn: 'root' })
export class RateSchedValdService {
    private resourceUrl = SERVER_API_URL + 'api/rate-sched-valds';

    constructor(private http: HttpClient) {}

    create(rateSchedVald: IRateSchedVald): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rateSchedVald);
        return this.http
            .post<IRateSchedVald>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(rateSchedVald: IRateSchedVald): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rateSchedVald);
        return this.http
            .put<IRateSchedVald>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRateSchedVald>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRateSchedVald[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(rateSchedVald: IRateSchedVald): IRateSchedVald {
        const copy: IRateSchedVald = Object.assign({}, rateSchedVald, {
            updateTimeStamp:
                rateSchedVald.updateTimeStamp != null && rateSchedVald.updateTimeStamp.isValid()
                    ? rateSchedVald.updateTimeStamp.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.updateTimeStamp = res.body.updateTimeStamp != null ? moment(res.body.updateTimeStamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((rateSchedVald: IRateSchedVald) => {
            rateSchedVald.updateTimeStamp = rateSchedVald.updateTimeStamp != null ? moment(rateSchedVald.updateTimeStamp) : null;
        });
        return res;
    }
}
