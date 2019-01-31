import { Moment } from 'moment';
import { IContrLoc } from 'app/shared/model//contr-loc.model';

export interface IContract {
    id?: number;
    contrId?: string;
    status?: string;
    updater?: string;
    updateTimeStamp?: Moment;
    businessUnit?: string;
    contrLocs?: IContrLoc[];
    rtSchedRateScheduleCD?: string;
    rtSchedId?: number;
    busAssocBaAbbr?: string;
    busAssocId?: number;
}

export class Contract implements IContract {
    constructor(
        public id?: number,
        public contrId?: string,
        public status?: string,
        public updater?: string,
        public updateTimeStamp?: Moment,
        public businessUnit?: string,
        public contrLocs?: IContrLoc[],
        public rtSchedRateScheduleCD?: string,
        public rtSchedId?: number,
        public busAssocBaAbbr?: string,
        public busAssocId?: number
    ) {}
}
