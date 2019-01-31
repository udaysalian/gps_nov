import { Moment } from 'moment';

export interface ILocationBA {
    id?: number;
    locationNbr?: string;
    locationBAType?: string;
    businessUnit?: string;
    updater?: string;
    updateTimestamp?: Moment;
    busAssocBaAbbr?: string;
    busAssocId?: number;
}

export class LocationBA implements ILocationBA {
    constructor(
        public id?: number,
        public locationNbr?: string,
        public locationBAType?: string,
        public businessUnit?: string,
        public updater?: string,
        public updateTimestamp?: Moment,
        public busAssocBaAbbr?: string,
        public busAssocId?: number
    ) {}
}
