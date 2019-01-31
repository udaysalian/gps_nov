import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ReductionReason } from 'app/shared/model/reduction-reason.model';
import { ReductionReasonService } from './reduction-reason.service';
import { ReductionReasonComponent } from './reduction-reason.component';
import { ReductionReasonDetailComponent } from './reduction-reason-detail.component';
import { ReductionReasonUpdateComponent } from './reduction-reason-update.component';
import { ReductionReasonDeletePopupComponent } from './reduction-reason-delete-dialog.component';
import { IReductionReason } from 'app/shared/model/reduction-reason.model';

@Injectable({ providedIn: 'root' })
export class ReductionReasonResolve implements Resolve<IReductionReason> {
    constructor(private service: ReductionReasonService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((reductionReason: HttpResponse<ReductionReason>) => reductionReason.body));
        }
        return of(new ReductionReason());
    }
}

export const reductionReasonRoute: Routes = [
    {
        path: 'reduction-reason',
        component: ReductionReasonComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ReductionReasons'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reduction-reason/:id/view',
        component: ReductionReasonDetailComponent,
        resolve: {
            reductionReason: ReductionReasonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ReductionReasons'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reduction-reason/new',
        component: ReductionReasonUpdateComponent,
        resolve: {
            reductionReason: ReductionReasonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ReductionReasons'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reduction-reason/:id/edit',
        component: ReductionReasonUpdateComponent,
        resolve: {
            reductionReason: ReductionReasonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ReductionReasons'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const reductionReasonPopupRoute: Routes = [
    {
        path: 'reduction-reason/:id/delete',
        component: ReductionReasonDeletePopupComponent,
        resolve: {
            reductionReason: ReductionReasonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ReductionReasons'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
