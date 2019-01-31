import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReductionReason } from 'app/shared/model/reduction-reason.model';

type EntityResponseType = HttpResponse<IReductionReason>;
type EntityArrayResponseType = HttpResponse<IReductionReason[]>;

@Injectable({ providedIn: 'root' })
export class ReductionReasonService {
    private resourceUrl = SERVER_API_URL + 'api/reduction-reasons';

    constructor(private http: HttpClient) {}

    create(reductionReason: IReductionReason): Observable<EntityResponseType> {
        return this.http.post<IReductionReason>(this.resourceUrl, reductionReason, { observe: 'response' });
    }

    update(reductionReason: IReductionReason): Observable<EntityResponseType> {
        return this.http.put<IReductionReason>(this.resourceUrl, reductionReason, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IReductionReason>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IReductionReason[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
