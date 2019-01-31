import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { RateSchedVald } from 'app/shared/model/rate-sched-vald.model';
import { RateSchedValdService } from './rate-sched-vald.service';
import { RateSchedValdComponent } from './rate-sched-vald.component';
import { RateSchedValdDetailComponent } from './rate-sched-vald-detail.component';
import { RateSchedValdUpdateComponent } from './rate-sched-vald-update.component';
import { RateSchedValdDeletePopupComponent } from './rate-sched-vald-delete-dialog.component';
import { IRateSchedVald } from 'app/shared/model/rate-sched-vald.model';

@Injectable({ providedIn: 'root' })
export class RateSchedValdResolve implements Resolve<IRateSchedVald> {
    constructor(private service: RateSchedValdService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((rateSchedVald: HttpResponse<RateSchedVald>) => rateSchedVald.body));
        }
        return of(new RateSchedVald());
    }
}

export const rateSchedValdRoute: Routes = [
    {
        path: 'rate-sched-vald',
        component: RateSchedValdComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RateSchedValds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rate-sched-vald/:id/view',
        component: RateSchedValdDetailComponent,
        resolve: {
            rateSchedVald: RateSchedValdResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RateSchedValds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rate-sched-vald/new',
        component: RateSchedValdUpdateComponent,
        resolve: {
            rateSchedVald: RateSchedValdResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RateSchedValds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rate-sched-vald/:id/edit',
        component: RateSchedValdUpdateComponent,
        resolve: {
            rateSchedVald: RateSchedValdResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RateSchedValds'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rateSchedValdPopupRoute: Routes = [
    {
        path: 'rate-sched-vald/:id/delete',
        component: RateSchedValdDeletePopupComponent,
        resolve: {
            rateSchedVald: RateSchedValdResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RateSchedValds'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
