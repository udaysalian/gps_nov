import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ContrLoc } from 'app/shared/model/contr-loc.model';
import { ContrLocService } from './contr-loc.service';
import { ContrLocComponent } from './contr-loc.component';
import { ContrLocDetailComponent } from './contr-loc-detail.component';
import { ContrLocUpdateComponent } from './contr-loc-update.component';
import { ContrLocDeletePopupComponent } from './contr-loc-delete-dialog.component';
import { IContrLoc } from 'app/shared/model/contr-loc.model';

@Injectable({ providedIn: 'root' })
export class ContrLocResolve implements Resolve<IContrLoc> {
    constructor(private service: ContrLocService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((contrLoc: HttpResponse<ContrLoc>) => contrLoc.body));
        }
        return of(new ContrLoc());
    }
}

export const contrLocRoute: Routes = [
    {
        path: 'contr-loc',
        component: ContrLocComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ContrLocs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'contr-loc/:id/view',
        component: ContrLocDetailComponent,
        resolve: {
            contrLoc: ContrLocResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ContrLocs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'contr-loc/new',
        component: ContrLocUpdateComponent,
        resolve: {
            contrLoc: ContrLocResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ContrLocs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'contr-loc/:id/edit',
        component: ContrLocUpdateComponent,
        resolve: {
            contrLoc: ContrLocResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ContrLocs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const contrLocPopupRoute: Routes = [
    {
        path: 'contr-loc/:id/delete',
        component: ContrLocDeletePopupComponent,
        resolve: {
            contrLoc: ContrLocResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ContrLocs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
