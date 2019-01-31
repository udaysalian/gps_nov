import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRateSched } from 'app/shared/model/rate-sched.model';

type EntityResponseType = HttpResponse<IRateSched>;
type EntityArrayResponseType = HttpResponse<IRateSched[]>;

@Injectable({ providedIn: 'root' })
export class RateSchedService {
    private resourceUrl = SERVER_API_URL + 'api/rate-scheds';

    constructor(private http: HttpClient) {}

    create(rateSched: IRateSched): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rateSched);
        return this.http
            .post<IRateSched>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(rateSched: IRateSched): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rateSched);
        return this.http
            .put<IRateSched>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRateSched>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRateSched[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(rateSched: IRateSched): IRateSched {
        const copy: IRateSched = Object.assign({}, rateSched, {
            updateTimeStamp:
                rateSched.updateTimeStamp != null && rateSched.updateTimeStamp.isValid() ? rateSched.updateTimeStamp.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.updateTimeStamp = res.body.updateTimeStamp != null ? moment(res.body.updateTimeStamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((rateSched: IRateSched) => {
            rateSched.updateTimeStamp = rateSched.updateTimeStamp != null ? moment(rateSched.updateTimeStamp) : null;
        });
        return res;
    }
}
