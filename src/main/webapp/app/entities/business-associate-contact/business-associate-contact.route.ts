import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BusinessAssociateContact } from 'app/shared/model/business-associate-contact.model';
import { BusinessAssociateContactService } from './business-associate-contact.service';
import { BusinessAssociateContactComponent } from './business-associate-contact.component';
import { BusinessAssociateContactDetailComponent } from './business-associate-contact-detail.component';
import { BusinessAssociateContactUpdateComponent } from './business-associate-contact-update.component';
import { BusinessAssociateContactDeletePopupComponent } from './business-associate-contact-delete-dialog.component';
import { IBusinessAssociateContact } from 'app/shared/model/business-associate-contact.model';

@Injectable({ providedIn: 'root' })
export class BusinessAssociateContactResolve implements Resolve<IBusinessAssociateContact> {
    constructor(private service: BusinessAssociateContactService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((businessAssociateContact: HttpResponse<BusinessAssociateContact>) => businessAssociateContact.body));
        }
        return of(new BusinessAssociateContact());
    }
}

export const businessAssociateContactRoute: Routes = [
    {
        path: 'business-associate-contact',
        component: BusinessAssociateContactComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociateContacts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-associate-contact/:id/view',
        component: BusinessAssociateContactDetailComponent,
        resolve: {
            businessAssociateContact: BusinessAssociateContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociateContacts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-associate-contact/new',
        component: BusinessAssociateContactUpdateComponent,
        resolve: {
            businessAssociateContact: BusinessAssociateContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociateContacts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'business-associate-contact/:id/edit',
        component: BusinessAssociateContactUpdateComponent,
        resolve: {
            businessAssociateContact: BusinessAssociateContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociateContacts'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const businessAssociateContactPopupRoute: Routes = [
    {
        path: 'business-associate-contact/:id/delete',
        component: BusinessAssociateContactDeletePopupComponent,
        resolve: {
            businessAssociateContact: BusinessAssociateContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BusinessAssociateContacts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
