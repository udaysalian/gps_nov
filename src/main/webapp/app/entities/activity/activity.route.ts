import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Activity } from 'app/shared/model/activity.model';
import { ActivityService } from './activity.service';
import { ActivityComponent } from './activity.component';
import { ActivityDetailComponent } from './activity-detail.component';
import { ActivityUpdateComponent } from './activity-update.component';
import { ActivityDeletePopupComponent } from './activity-delete-dialog.component';
import { IActivity } from 'app/shared/model/activity.model';

@Injectable({ providedIn: 'root' })
export class ActivityResolve implements Resolve<IActivity> {
    constructor(private service: ActivityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((activity: HttpResponse<Activity>) => activity.body));
        }
        return of(new Activity());
    }
}

export const activityRoute: Routes = [
    {
        path: 'activity',
        component: ActivityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Activities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'activity/:id/view',
        component: ActivityDetailComponent,
        resolve: {
            activity: ActivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Activities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'activity/new',
        component: ActivityUpdateComponent,
        resolve: {
            activity: ActivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Activities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'activity/:id/edit',
        component: ActivityUpdateComponent,
        resolve: {
            activity: ActivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Activities'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const activityPopupRoute: Routes = [
    {
        path: 'activity/:id/delete',
        component: ActivityDeletePopupComponent,
        resolve: {
            activity: ActivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Activities'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
