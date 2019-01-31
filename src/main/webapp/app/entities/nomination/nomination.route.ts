import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Nomination } from 'app/shared/model/nomination.model';
import { NominationService } from './netraNomination.service';
import { NominationComponent } from './nomination.component';
import { NominationDetailComponent } from './nomination-detail.component';
import { NominationUpdateComponent } from './nomination-update.component';
import { NominationDeletePopupComponent } from './nomination-delete-dialog.component';
import { INomination } from 'app/shared/model/nomination.model';

@Injectable({ providedIn: 'root' })
export class NominationResolve implements Resolve<INomination> {
    constructor(private service: NominationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((nomination: HttpResponse<Nomination>) => nomination.body));
        }
        return of(new Nomination());
    }
}

export const nominationRoute: Routes = [
    {
        path: 'nomination',
        component: NominationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Nominations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nomination/:id/view',
        component: NominationDetailComponent,
        resolve: {
            nomination: NominationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Nominations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nomination/new',
        component: NominationUpdateComponent,
        resolve: {
            nomination: NominationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Nominations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nomination/:id/edit',
        component: NominationUpdateComponent,
        resolve: {
            nomination: NominationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Nominations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nominationPopupRoute: Routes = [
    {
        path: 'nomination/:id/delete',
        component: NominationDeletePopupComponent,
        resolve: {
            nomination: NominationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Nominations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
