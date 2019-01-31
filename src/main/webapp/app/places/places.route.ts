import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { PlacesComponent } from './';

export const PLACES_ROUTE: Route = {
  path: 'places',
  component: PlacesComponent,
  data: {
    authorities: [],
    pageTitle: 'places.title'
  },
  canActivate: [UserRouteAccessService]
};
