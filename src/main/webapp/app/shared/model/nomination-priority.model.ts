import { Moment } from 'moment';

export interface INominationPriority {
    id?: number;
    gasDate?: Moment;
    prtyTp?: string;
    oldQty?: number;
    newQty?: number;
    subType?: string;
    dirOfFlow?: string;
    updater?: string;
    updateTimeStamp?: Moment;
    businessUnit?: string;
    nominationId?: number;
    activityActivityNbr?: string;
    activityId?: number;
    contractContrId?: string;
    contractId?: number;
}

export class NominationPriority implements INominationPriority {
    constructor(
        public id?: number,
        public gasDate?: Moment,
        public prtyTp?: string,
        public oldQty?: number,
        public newQty?: number,
        public subType?: string,
        public dirOfFlow?: string,
        public updater?: string,
        public updateTimeStamp?: Moment,
        public businessUnit?: string,
        public nominationId?: number,
        public activityActivityNbr?: string,
        public activityId?: number,
        public contractContrId?: string,
        public contractId?: number
    ) {}
}
