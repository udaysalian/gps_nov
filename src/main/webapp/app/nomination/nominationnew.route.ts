import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { NominationNewComponent } from './';

export const NOMINATION_ROUTE: Route = {
  path: 'nomination_new',
  component: NominationNewComponent,
  data: {
    authorities: [],
    pageTitle: 'nomination.title'
  },
  canActivate: [UserRouteAccessService]
};
