import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NetraSharedModule } from '../shared';

import { PLACES_ROUTE, PlacesComponent } from './';

@NgModule({
    imports: [
      NetraSharedModule,
      RouterModule.forRoot([ PLACES_ROUTE ], { useHash: true })
    ],
    declarations: [
      PlacesComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NetraAppPlacesModule {}
