import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IContrLoc } from 'app/shared/model/contr-loc.model';

type EntityResponseType = HttpResponse<IContrLoc>;
type EntityArrayResponseType = HttpResponse<IContrLoc[]>;

@Injectable({ providedIn: 'root' })
export class ContrLocService {
    private resourceUrl = SERVER_API_URL + 'api/contr-locs';

    constructor(private http: HttpClient) {}

    create(contrLoc: IContrLoc): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(contrLoc);
        return this.http
            .post<IContrLoc>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(contrLoc: IContrLoc): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(contrLoc);
        return this.http
            .put<IContrLoc>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IContrLoc>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IContrLoc[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(contrLoc: IContrLoc): IContrLoc {
        const copy: IContrLoc = Object.assign({}, contrLoc, {
            effectiveFromDate:
                contrLoc.effectiveFromDate != null && contrLoc.effectiveFromDate.isValid()
                    ? contrLoc.effectiveFromDate.format(DATE_FORMAT)
                    : null,
            effectiveToDate:
                contrLoc.effectiveToDate != null && contrLoc.effectiveToDate.isValid()
                    ? contrLoc.effectiveToDate.format(DATE_FORMAT)
                    : null,
            updateTimeStamp:
                contrLoc.updateTimeStamp != null && contrLoc.updateTimeStamp.isValid() ? contrLoc.updateTimeStamp.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.effectiveFromDate = res.body.effectiveFromDate != null ? moment(res.body.effectiveFromDate) : null;
        res.body.effectiveToDate = res.body.effectiveToDate != null ? moment(res.body.effectiveToDate) : null;
        res.body.updateTimeStamp = res.body.updateTimeStamp != null ? moment(res.body.updateTimeStamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((contrLoc: IContrLoc) => {
            contrLoc.effectiveFromDate = contrLoc.effectiveFromDate != null ? moment(contrLoc.effectiveFromDate) : null;
            contrLoc.effectiveToDate = contrLoc.effectiveToDate != null ? moment(contrLoc.effectiveToDate) : null;
            contrLoc.updateTimeStamp = contrLoc.updateTimeStamp != null ? moment(contrLoc.updateTimeStamp) : null;
        });
        return res;
    }
}
