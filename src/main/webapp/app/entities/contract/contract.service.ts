import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IContract } from 'app/shared/model/contract.model';

type EntityResponseType = HttpResponse<IContract>;
type EntityArrayResponseType = HttpResponse<IContract[]>;

@Injectable({ providedIn: 'root' })
export class ContractService {
    private resourceUrl = SERVER_API_URL + 'api/gps_contracts';

    constructor(private http: HttpClient) {}

    create(contract: IContract): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(contract);
        return this.http
            .post<IContract>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(contract: IContract): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(contract);
        return this.http
            .put<IContract>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IContract>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IContract[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(contract: IContract): IContract {
        const copy: IContract = Object.assign({}, contract, {
            updateTimeStamp:
                contract.updateTimeStamp != null && contract.updateTimeStamp.isValid() ? contract.updateTimeStamp.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.updateTimeStamp = res.body.updateTimeStamp != null ? moment(res.body.updateTimeStamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((contract: IContract) => {
            contract.updateTimeStamp = contract.updateTimeStamp != null ? moment(contract.updateTimeStamp) : null;
        });
        return res;
    }
}
