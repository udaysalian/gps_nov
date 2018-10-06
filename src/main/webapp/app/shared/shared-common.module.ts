import { NgModule } from '@angular/core';

import { NetraSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [NetraSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [NetraSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class NetraSharedCommonModule {}
