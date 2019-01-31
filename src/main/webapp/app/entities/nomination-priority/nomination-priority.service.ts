import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INominationPriority } from 'app/shared/model/nomination-priority.model';

type EntityResponseType = HttpResponse<INominationPriority>;
type EntityArrayResponseType = HttpResponse<INominationPriority[]>;

@Injectable({ providedIn: 'root' })
export class NominationPriorityService {
    private resourceUrl = SERVER_API_URL + 'api/nomination-priorities';

    constructor(private http: HttpClient) {}

    create(nominationPriority: INominationPriority): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nominationPriority);
        return this.http
            .post<INominationPriority>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(nominationPriority: INominationPriority): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nominationPriority);
        return this.http
            .put<INominationPriority>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INominationPriority>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INominationPriority[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(nominationPriority: INominationPriority): INominationPriority {
        const copy: INominationPriority = Object.assign({}, nominationPriority, {
            gasDate:
                nominationPriority.gasDate != null && nominationPriority.gasDate.isValid()
                    ? nominationPriority.gasDate.format(DATE_FORMAT)
                    : null,
            updateTimeStamp:
                nominationPriority.updateTimeStamp != null && nominationPriority.updateTimeStamp.isValid()
                    ? nominationPriority.updateTimeStamp.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.gasDate = res.body.gasDate != null ? moment(res.body.gasDate) : null;
        res.body.updateTimeStamp = res.body.updateTimeStamp != null ? moment(res.body.updateTimeStamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((nominationPriority: INominationPriority) => {
            nominationPriority.gasDate = nominationPriority.gasDate != null ? moment(nominationPriority.gasDate) : null;
            nominationPriority.updateTimeStamp =
                nominationPriority.updateTimeStamp != null ? moment(nominationPriority.updateTimeStamp) : null;
        });
        return res;
    }
}
