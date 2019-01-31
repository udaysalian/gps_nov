import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILocationBA } from 'app/shared/model/location-ba.model';

type EntityResponseType = HttpResponse<ILocationBA>;
type EntityArrayResponseType = HttpResponse<ILocationBA[]>;

@Injectable({ providedIn: 'root' })
export class LocationBAService {
    private resourceUrl = SERVER_API_URL + 'api/location-bas';

    constructor(private http: HttpClient) {}

    create(locationBA: ILocationBA): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(locationBA);
        return this.http
            .post<ILocationBA>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(locationBA: ILocationBA): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(locationBA);
        return this.http
            .put<ILocationBA>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILocationBA>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILocationBA[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(locationBA: ILocationBA): ILocationBA {
        const copy: ILocationBA = Object.assign({}, locationBA, {
            updateTimestamp:
                locationBA.updateTimestamp != null && locationBA.updateTimestamp.isValid() ? locationBA.updateTimestamp.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.updateTimestamp = res.body.updateTimestamp != null ? moment(res.body.updateTimestamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((locationBA: ILocationBA) => {
            locationBA.updateTimestamp = locationBA.updateTimestamp != null ? moment(locationBA.updateTimestamp) : null;
        });
        return res;
    }
}
