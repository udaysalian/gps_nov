import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { PathNominationComponent } from './';

export const PATH_NOMINATION_ROUTE: Route = {
  path: 'path-nomination',
  component: PathNominationComponent,
  data: {
    authorities: [],
    pageTitle: 'path-nomination.title'
  },
  canActivate: [UserRouteAccessService]
};
