import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NetraSharedModule } from 'app/shared';
import {
    ReductionReasonComponent,
    ReductionReasonDetailComponent,
    ReductionReasonUpdateComponent,
    ReductionReasonDeletePopupComponent,
    ReductionReasonDeleteDialogComponent,
    reductionReasonRoute,
    reductionReasonPopupRoute
} from './';

const ENTITY_STATES = [...reductionReasonRoute, ...reductionReasonPopupRoute];

@NgModule({
    imports: [NetraSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReductionReasonComponent,
        ReductionReasonDetailComponent,
        ReductionReasonUpdateComponent,
        ReductionReasonDeleteDialogComponent,
        ReductionReasonDeletePopupComponent
    ],
    entryComponents: [
        ReductionReasonComponent,
        ReductionReasonUpdateComponent,
        ReductionReasonDeleteDialogComponent,
        ReductionReasonDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NetraReductionReasonModule {}
