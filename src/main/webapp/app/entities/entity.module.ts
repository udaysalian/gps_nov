import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { NetraNominationModule } from './nomination/nomination.module';
import { NetraReductionReasonModule } from './reduction-reason/reduction-reason.module';
import { NetraActivityModule } from './activity/activity.module';
import { NetraRateSchedModule } from './rate-sched/rate-sched.module';
import { NetraRateSchedValdModule } from './rate-sched-vald/rate-sched-vald.module';
import { NetraNominationPriorityModule } from './nomination-priority/nomination-priority.module';
import { NetraContractModule } from './contract/contract.module';
import { NetraContrLocModule } from './contr-loc/contr-loc.module';
import { NetraBusinessAssociateModule } from './business-associate/business-associate.module';
import { NetraBusinessAssociateAddressModule } from './business-associate-address/business-associate-address.module';
import { NetraMeasurementStationModule } from './measurement-station/measurement-station.module';
import { NetraLocationBAModule } from './location-ba/location-ba.module';
import { NetraContactModule } from './contact/contact.module';
import { NetraBusinessUnitModule } from './business-unit/business-unit.module';
import { NetraBusinessAssociateContactModule } from './business-associate-contact/business-associate-contact.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        NetraNominationModule,
        NetraReductionReasonModule,
        NetraActivityModule,
        NetraRateSchedModule,
        NetraRateSchedValdModule,
        NetraNominationPriorityModule,
        NetraContractModule,
        NetraContrLocModule,
        NetraBusinessAssociateModule,
        NetraBusinessAssociateAddressModule,
        NetraMeasurementStationModule,
        NetraLocationBAModule,
        NetraContactModule,
        NetraBusinessUnitModule,
        NetraBusinessAssociateContactModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NetraEntityModule {}
