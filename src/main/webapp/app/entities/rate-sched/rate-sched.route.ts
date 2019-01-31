import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { RateSched } from 'app/shared/model/rate-sched.model';
import { RateSchedService } from './rate-sched.service';
import { RateSchedComponent } from './rate-sched.component';
import { RateSchedDetailComponent } from './rate-sched-detail.component';
import { RateSchedUpdateComponent } from './rate-sched-update.component';
import { RateSchedDeletePopupComponent } from './rate-sched-delete-dialog.component';
import { IRateSched } from 'app/shared/model/rate-sched.model';

@Injectable({ providedIn: 'root' })
export class RateSchedResolve implements Resolve<IRateSched> {
    constructor(private service: RateSchedService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((rateSched: HttpResponse<RateSched>) => rateSched.body));
        }
        return of(new RateSched());
    }
}

export const rateSchedRoute: Routes = [
    {
        path: 'rate-sched',
        component: RateSchedComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RateScheds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rate-sched/:id/view',
        component: RateSchedDetailComponent,
        resolve: {
            rateSched: RateSchedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RateScheds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rate-sched/new',
        component: RateSchedUpdateComponent,
        resolve: {
            rateSched: RateSchedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RateScheds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rate-sched/:id/edit',
        component: RateSchedUpdateComponent,
        resolve: {
            rateSched: RateSchedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RateScheds'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rateSchedPopupRoute: Routes = [
    {
        path: 'rate-sched/:id/delete',
        component: RateSchedDeletePopupComponent,
        resolve: {
            rateSched: RateSchedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RateScheds'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
