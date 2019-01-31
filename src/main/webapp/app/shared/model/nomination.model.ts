import { Moment } from 'moment';
import { INominationPriority } from 'app/shared/model//nomination-priority.model';

export const enum NomRequestStatus {
    NOMINATED = 'NOMINATED',
    REJECTED = 'REJECTED',
    ACCEPTED = 'ACCEPTED'
}

export interface INomination {
    id?: number;
    gasDate?: Moment;
    requestedRcptQty?: number;
    reqestedDlvryQty?: number;
    scheduledRcptQty?: number;
    scheduledDlvryQty?: number;
    requestStatus?: NomRequestStatus;
    updater?: string;
    updateTimeStamp?: Moment;
    businessUnit?: string;
    activityActivityNbr?: string;
    activityId?: number;
    contractContrId?: string;
    contractId?: number;
    priorities?: INominationPriority[];
}

export class Nomination implements INomination {
    constructor(
        public id?: number,
        public gasDate?: Moment,
        public requestedRcptQty?: number,
        public reqestedDlvryQty?: number,
        public scheduledRcptQty?: number,
        public scheduledDlvryQty?: number,
        public requestStatus?: NomRequestStatus,
        public updater?: string,
        public updateTimeStamp?: Moment,
        public businessUnit?: string,
        public activityActivityNbr?: string,
        public activityId?: number,
        public contractContrId?: string,
        public contractId?: number,
        public priorities?: INominationPriority[]
    ) {}
}
