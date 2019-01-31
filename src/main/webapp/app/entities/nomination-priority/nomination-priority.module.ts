import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NetraSharedModule } from 'app/shared';
import {
    NominationPriorityComponent,
    NominationPriorityDetailComponent,
    NominationPriorityUpdateComponent,
    NominationPriorityDeletePopupComponent,
    NominationPriorityDeleteDialogComponent,
    nominationPriorityRoute,
    nominationPriorityPopupRoute
} from './';

const ENTITY_STATES = [...nominationPriorityRoute, ...nominationPriorityPopupRoute];

@NgModule({
    imports: [NetraSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NominationPriorityComponent,
        NominationPriorityDetailComponent,
        NominationPriorityUpdateComponent,
        NominationPriorityDeleteDialogComponent,
        NominationPriorityDeletePopupComponent
    ],
    entryComponents: [
        NominationPriorityComponent,
        NominationPriorityUpdateComponent,
        NominationPriorityDeleteDialogComponent,
        NominationPriorityDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NetraNominationPriorityModule {}
