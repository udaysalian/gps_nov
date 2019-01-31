import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NetraSharedModule } from '../shared';

import { NOMINATION_ROUTE, NominationNewComponent } from './';
import {AgGridModule} from 'ag-grid-angular';

@NgModule({
    imports: [
      NetraSharedModule,
        AgGridModule.withComponents(null),
      RouterModule.forRoot([ NOMINATION_ROUTE ], { useHash: true })
    ],
    declarations: [
        NominationNewComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NetraAppNominationNewModule {}
