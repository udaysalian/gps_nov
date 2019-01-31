import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BusinessUnit } from 'app/shared/model/business-unit.model';
import { BusinessUnitService } from './business-unit.service';
import { BusinessUnitComponent } from './business-unit.component';
import { BusinessUnitDetailComponent } from './business-unit-detail.component';
import { BusinessUnitUpdateComponent } from './business-unit-update.component';
import { BusinessUnitDeletePopupComponent } from './business-unit-delete-dialog.component';
import { IBusinessUnit } from 'app/shared/model/business-unit.model';

@Injectable({ providedIn: 'root' })
export class BusinessUnitResolve implements Resolve<IBusinessUnit> {
    constructor(private service: BusinessUnitService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((businessUnit: HttpResponse<BusinessUnit>) => businessUnit.body));
        }
        return of(new BusinessUnit());
    }
}

export const businessUnitRoute: Routes = [
    {
        path: 'business-unit',
        component: BusinessUnitComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessUnits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-unit/:id/view',
        component: BusinessUnitDetailComponent,
        resolve: {
            businessUnit: BusinessUnitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessUnits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-unit/new',
        component: BusinessUnitUpdateComponent,
        resolve: {
            businessUnit: BusinessUnitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessUnits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-unit/:id/edit',
        component: BusinessUnitUpdateComponent,
        resolve: {
            businessUnit: BusinessUnitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessUnits'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const businessUnitPopupRoute: Routes = [
    {
        path: 'business-unit/:id/delete',
        component: BusinessUnitDeletePopupComponent,
        resolve: {
            businessUnit: BusinessUnitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessUnits'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
