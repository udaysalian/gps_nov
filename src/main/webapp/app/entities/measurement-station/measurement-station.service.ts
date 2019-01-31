import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMeasurementStation } from 'app/shared/model/measurement-station.model';

type EntityResponseType = HttpResponse<IMeasurementStation>;
type EntityArrayResponseType = HttpResponse<IMeasurementStation[]>;

@Injectable({ providedIn: 'root' })
export class MeasurementStationService {
    private resourceUrl = SERVER_API_URL + 'api/measurement-stations';

    constructor(private http: HttpClient) {}

    create(measurementStation: IMeasurementStation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(measurementStation);
        return this.http
            .post<IMeasurementStation>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(measurementStation: IMeasurementStation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(measurementStation);
        return this.http
            .put<IMeasurementStation>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMeasurementStation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMeasurementStation[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(measurementStation: IMeasurementStation): IMeasurementStation {
        const copy: IMeasurementStation = Object.assign({}, measurementStation, {
            updateTimestamp:
                measurementStation.updateTimestamp != null && measurementStation.updateTimestamp.isValid()
                    ? measurementStation.updateTimestamp.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.updateTimestamp = res.body.updateTimestamp != null ? moment(res.body.updateTimestamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((measurementStation: IMeasurementStation) => {
            measurementStation.updateTimestamp =
                measurementStation.updateTimestamp != null ? moment(measurementStation.updateTimestamp) : null;
        });
        return res;
    }
}
