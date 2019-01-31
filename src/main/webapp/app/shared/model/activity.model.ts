import { Moment } from 'moment';

export interface IActivity {
    id?: number;
    contrId?: string;
    activityNbr?: string;
    transactionType?: string;
    updater?: string;
    updateTimeStamp?: Moment;
    businessUnit?: string;
    upstreamBABaAbbr?: string;
    upstreamBAId?: number;
    downstreamBABaAbbr?: string;
    downstreamBAId?: number;
    rtSchedRateScheduleCD?: string;
    rtSchedId?: number;
    receiptLocationLocationNbr?: string;
    receiptLocationId?: number;
    deliveryLocationLocationNbr?: string;
    deliveryLocationId?: number;
    upstreamContractContrId?: string;
    upstreamContractId?: number;
    downstreamContractContrId?: string;
    downstreamContractId?: number;
}

export class Activity implements IActivity {
    constructor(
        public id?: number,
        public contrId?: string,
        public activityNbr?: string,
        public transactionType?: string,
        public updater?: string,
        public updateTimeStamp?: Moment,
        public businessUnit?: string,
        public upstreamBABaAbbr?: string,
        public upstreamBAId?: number,
        public downstreamBABaAbbr?: string,
        public downstreamBAId?: number,
        public rtSchedRateScheduleCD?: string,
        public rtSchedId?: number,
        public receiptLocationLocationNbr?: string,
        public receiptLocationId?: number,
        public deliveryLocationLocationNbr?: string,
        public deliveryLocationId?: number,
        public upstreamContractContrId?: string,
        public upstreamContractId?: number,
        public downstreamContractContrId?: string,
        public downstreamContractId?: number
    ) {}
}
