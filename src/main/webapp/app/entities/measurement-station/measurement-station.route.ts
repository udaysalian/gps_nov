import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MeasurementStation } from 'app/shared/model/measurement-station.model';
import { MeasurementStationService } from './measurement-station.service';
import { MeasurementStationComponent } from './measurement-station.component';
import { MeasurementStationDetailComponent } from './measurement-station-detail.component';
import { MeasurementStationUpdateComponent } from './measurement-station-update.component';
import { MeasurementStationDeletePopupComponent } from './measurement-station-delete-dialog.component';
import { IMeasurementStation } from 'app/shared/model/measurement-station.model';

@Injectable({ providedIn: 'root' })
export class MeasurementStationResolve implements Resolve<IMeasurementStation> {
    constructor(private service: MeasurementStationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((measurementStation: HttpResponse<MeasurementStation>) => measurementStation.body));
        }
        return of(new MeasurementStation());
    }
}

export const measurementStationRoute: Routes = [
    {
        path: 'measurement-station',
        component: MeasurementStationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MeasurementStations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'measurement-station/:id/view',
        component: MeasurementStationDetailComponent,
        resolve: {
            measurementStation: MeasurementStationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MeasurementStations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'measurement-station/new',
        component: MeasurementStationUpdateComponent,
        resolve: {
            measurementStation: MeasurementStationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MeasurementStations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'measurement-station/:id/edit',
        component: MeasurementStationUpdateComponent,
        resolve: {
            measurementStation: MeasurementStationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MeasurementStations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const measurementStationPopupRoute: Routes = [
    {
        path: 'measurement-station/:id/delete',
        component: MeasurementStationDeletePopupComponent,
        resolve: {
            measurementStation: MeasurementStationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MeasurementStations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
