import { Moment } from 'moment';
import { IRateSchedVald } from 'app/shared/model//rate-sched-vald.model';

export interface IRateSched {
    id?: number;
    rsType?: string;
    rateScheduleCD?: string;
    updater?: string;
    updateTimeStamp?: Moment;
    businessUnit?: string;
    rtSchedValds?: IRateSchedVald[];
}

export class RateSched implements IRateSched {
    constructor(
        public id?: number,
        public rsType?: string,
        public rateScheduleCD?: string,
        public updater?: string,
        public updateTimeStamp?: Moment,
        public businessUnit?: string,
        public rtSchedValds?: IRateSchedVald[]
    ) {}
}
