import { Moment } from 'moment';

export interface IRateSchedVald {
    id?: number;
    validType?: string;
    updater?: string;
    updateTimeStamp?: Moment;
    businessUnit?: string;
    rateSchdRateScheduleCD?: string;
    rateSchdId?: number;
}

export class RateSchedVald implements IRateSchedVald {
    constructor(
        public id?: number,
        public validType?: string,
        public updater?: string,
        public updateTimeStamp?: Moment,
        public businessUnit?: string,
        public rateSchdRateScheduleCD?: string,
        public rateSchdId?: number
    ) {}
}
