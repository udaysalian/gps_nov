import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NetraSharedModule } from 'app/shared';
import {
    NominationComponent,
    NominationDetailComponent,
    NominationUpdateComponent,
    NominationDeletePopupComponent,
    NominationDeleteDialogComponent,
    nominationRoute,
    nominationPopupRoute
} from './';

const ENTITY_STATES = [...nominationRoute, ...nominationPopupRoute];

@NgModule({
    imports: [NetraSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NominationComponent,
        NominationDetailComponent,
        NominationUpdateComponent,
        NominationDeleteDialogComponent,
        NominationDeletePopupComponent
    ],
    entryComponents: [NominationComponent, NominationUpdateComponent, NominationDeleteDialogComponent, NominationDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NetraNominationModule {}
