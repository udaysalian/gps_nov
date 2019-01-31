import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BusinessAssociate } from 'app/shared/model/business-associate.model';
import { BusinessAssociateService } from './business-associate.service';
import { BusinessAssociateComponent } from './business-associate.component';
import { BusinessAssociateDetailComponent } from './business-associate-detail.component';
import { BusinessAssociateUpdateComponent } from './business-associate-update.component';
import { BusinessAssociateDeletePopupComponent } from './business-associate-delete-dialog.component';
import { IBusinessAssociate } from 'app/shared/model/business-associate.model';

@Injectable({ providedIn: 'root' })
export class BusinessAssociateResolve implements Resolve<IBusinessAssociate> {
    constructor(private service: BusinessAssociateService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((businessAssociate: HttpResponse<BusinessAssociate>) => businessAssociate.body));
        }
        return of(new BusinessAssociate());
    }
}

export const businessAssociateRoute: Routes = [
    {
        path: 'business-associate',
        component: BusinessAssociateComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociates'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-associate/:id/view',
        component: BusinessAssociateDetailComponent,
        resolve: {
            businessAssociate: BusinessAssociateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociates'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-associate/new',
        component: BusinessAssociateUpdateComponent,
        resolve: {
            businessAssociate: BusinessAssociateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociates'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-associate/:id/edit',
        component: BusinessAssociateUpdateComponent,
        resolve: {
            businessAssociate: BusinessAssociateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociates'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const businessAssociatePopupRoute: Routes = [
    {
        path: 'business-associate/:id/delete',
        component: BusinessAssociateDeletePopupComponent,
        resolve: {
            businessAssociate: BusinessAssociateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociates'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
