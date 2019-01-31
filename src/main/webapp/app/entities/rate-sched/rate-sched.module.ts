import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NetraSharedModule } from 'app/shared';
import {
    RateSchedComponent,
    RateSchedDetailComponent,
    RateSchedUpdateComponent,
    RateSchedDeletePopupComponent,
    RateSchedDeleteDialogComponent,
    rateSchedRoute,
    rateSchedPopupRoute
} from './';

const ENTITY_STATES = [...rateSchedRoute, ...rateSchedPopupRoute];

@NgModule({
    imports: [NetraSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RateSchedComponent,
        RateSchedDetailComponent,
        RateSchedUpdateComponent,
        RateSchedDeleteDialogComponent,
        RateSchedDeletePopupComponent
    ],
    entryComponents: [RateSchedComponent, RateSchedUpdateComponent, RateSchedDeleteDialogComponent, RateSchedDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NetraRateSchedModule {}
