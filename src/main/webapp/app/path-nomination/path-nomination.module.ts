import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NetraSharedModule } from '../shared';
import {AgGridModule} from 'ag-grid-angular';
import { PATH_NOMINATION_ROUTE, PathNominationComponent } from './';

@NgModule({
    imports: [
      NetraSharedModule,
      AgGridModule.withComponents(null),
      RouterModule.forRoot([ PATH_NOMINATION_ROUTE ], { useHash: true })
    ],
    declarations: [
      PathNominationComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NetraAppPathNominationModule {}
