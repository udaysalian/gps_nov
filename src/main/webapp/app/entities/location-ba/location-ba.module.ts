import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NetraSharedModule } from 'app/shared';
import {
    LocationBAComponent,
    LocationBADetailComponent,
    LocationBAUpdateComponent,
    LocationBADeletePopupComponent,
    LocationBADeleteDialogComponent,
    locationBARoute,
    locationBAPopupRoute
} from './';

const ENTITY_STATES = [...locationBARoute, ...locationBAPopupRoute];

@NgModule({
    imports: [NetraSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LocationBAComponent,
        LocationBADetailComponent,
        LocationBAUpdateComponent,
        LocationBADeleteDialogComponent,
        LocationBADeletePopupComponent
    ],
    entryComponents: [LocationBAComponent, LocationBAUpdateComponent, LocationBADeleteDialogComponent, LocationBADeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NetraLocationBAModule {}
