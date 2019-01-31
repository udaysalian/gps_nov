import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BusinessAssociateAddress } from 'app/shared/model/business-associate-address.model';
import { BusinessAssociateAddressService } from './business-associate-address.service';
import { BusinessAssociateAddressComponent } from './business-associate-address.component';
import { BusinessAssociateAddressDetailComponent } from './business-associate-address-detail.component';
import { BusinessAssociateAddressUpdateComponent } from './business-associate-address-update.component';
import { BusinessAssociateAddressDeletePopupComponent } from './business-associate-address-delete-dialog.component';
import { IBusinessAssociateAddress } from 'app/shared/model/business-associate-address.model';

@Injectable({ providedIn: 'root' })
export class BusinessAssociateAddressResolve implements Resolve<IBusinessAssociateAddress> {
    constructor(private service: BusinessAssociateAddressService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((businessAssociateAddress: HttpResponse<BusinessAssociateAddress>) => businessAssociateAddress.body));
        }
        return of(new BusinessAssociateAddress());
    }
}

export const businessAssociateAddressRoute: Routes = [
    {
        path: 'business-associate-address',
        component: BusinessAssociateAddressComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociateAddresses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-associate-address/:id/view',
        component: BusinessAssociateAddressDetailComponent,
        resolve: {
            businessAssociateAddress: BusinessAssociateAddressResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociateAddresses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-associate-address/new',
        component: BusinessAssociateAddressUpdateComponent,
        resolve: {
            businessAssociateAddress: BusinessAssociateAddressResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociateAddresses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-associate-address/:id/edit',
        component: BusinessAssociateAddressUpdateComponent,
        resolve: {
            businessAssociateAddress: BusinessAssociateAddressResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociateAddresses'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const businessAssociateAddressPopupRoute: Routes = [
    {
        path: 'business-associate-address/:id/delete',
        component: BusinessAssociateAddressDeletePopupComponent,
        resolve: {
            businessAssociateAddress: BusinessAssociateAddressResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociateAddresses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
