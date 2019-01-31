import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LocationBA } from 'app/shared/model/location-ba.model';
import { LocationBAService } from './location-ba.service';
import { LocationBAComponent } from './location-ba.component';
import { LocationBADetailComponent } from './location-ba-detail.component';
import { LocationBAUpdateComponent } from './location-ba-update.component';
import { LocationBADeletePopupComponent } from './location-ba-delete-dialog.component';
import { ILocationBA } from 'app/shared/model/location-ba.model';

@Injectable({ providedIn: 'root' })
export class LocationBAResolve implements Resolve<ILocationBA> {
    constructor(private service: LocationBAService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((locationBA: HttpResponse<LocationBA>) => locationBA.body));
        }
        return of(new LocationBA());
    }
}

export const locationBARoute: Routes = [
    {
        path: 'location-ba',
        component: LocationBAComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LocationBAS'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'location-ba/:id/view',
        component: LocationBADetailComponent,
        resolve: {
            locationBA: LocationBAResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LocationBAS'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'location-ba/new',
        component: LocationBAUpdateComponent,
        resolve: {
            locationBA: LocationBAResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LocationBAS'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'location-ba/:id/edit',
        component: LocationBAUpdateComponent,
        resolve: {
            locationBA: LocationBAResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LocationBAS'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const locationBAPopupRoute: Routes = [
    {
        path: 'location-ba/:id/delete',
        component: LocationBADeletePopupComponent,
        resolve: {
            locationBA: LocationBAResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LocationBAS'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
