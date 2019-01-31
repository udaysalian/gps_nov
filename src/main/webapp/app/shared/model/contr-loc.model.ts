import { Moment } from 'moment';

export interface IContrLoc {
    id?: number;
    type?: string;
    quantity?: number;
    effectiveFromDate?: Moment;
    effectiveToDate?: Moment;
    updater?: string;
    updateTimeStamp?: Moment;
    businessUnit?: string;
    contractId?: number;
    locationLocationNbr?: string;
    locationId?: number;
}

export class ContrLoc implements IContrLoc {
    constructor(
        public id?: number,
        public type?: string,
        public quantity?: number,
        public effectiveFromDate?: Moment,
        public effectiveToDate?: Moment,
        public updater?: string,
        public updateTimeStamp?: Moment,
        public businessUnit?: string,
        public contractId?: number,
        public locationLocationNbr?: string,
        public locationId?: number
    ) {}
}
