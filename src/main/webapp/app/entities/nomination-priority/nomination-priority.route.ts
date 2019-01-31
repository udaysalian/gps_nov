import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NominationPriority } from 'app/shared/model/nomination-priority.model';
import { NominationPriorityService } from './nomination-priority.service';
import { NominationPriorityComponent } from './nomination-priority.component';
import { NominationPriorityDetailComponent } from './nomination-priority-detail.component';
import { NominationPriorityUpdateComponent } from './nomination-priority-update.component';
import { NominationPriorityDeletePopupComponent } from './nomination-priority-delete-dialog.component';
import { INominationPriority } from 'app/shared/model/nomination-priority.model';

@Injectable({ providedIn: 'root' })
export class NominationPriorityResolve implements Resolve<INominationPriority> {
    constructor(private service: NominationPriorityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((nominationPriority: HttpResponse<NominationPriority>) => nominationPriority.body));
        }
        return of(new NominationPriority());
    }
}

export const nominationPriorityRoute: Routes = [
    {
        path: 'nomination-priority',
        component: NominationPriorityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'NominationPriorities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nomination-priority/:id/view',
        component: NominationPriorityDetailComponent,
        resolve: {
            nominationPriority: NominationPriorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'NominationPriorities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nomination-priority/new',
        component: NominationPriorityUpdateComponent,
        resolve: {
            nominationPriority: NominationPriorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'NominationPriorities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nomination-priority/:id/edit',
        component: NominationPriorityUpdateComponent,
        resolve: {
            nominationPriority: NominationPriorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'NominationPriorities'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nominationPriorityPopupRoute: Routes = [
    {
        path: 'nomination-priority/:id/delete',
        component: NominationPriorityDeletePopupComponent,
        resolve: {
            nominationPriority: NominationPriorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'NominationPriorities'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
