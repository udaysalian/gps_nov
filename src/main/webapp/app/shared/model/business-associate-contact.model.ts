import { Moment } from 'moment';

export interface IBusinessAssociateContact {
    id?: number;
    beginDate?: Moment;
    endDate?: Moment;
    businessAssociateBaAbbr?: string;
    businessAssociateId?: number;
    mailAddressId?: number;
    deliveryAddressId?: number;
    contactId?: number;
}

export class BusinessAssociateContact implements IBusinessAssociateContact {
    constructor(
        public id?: number,
        public beginDate?: Moment,
        public endDate?: Moment,
        public businessAssociateBaAbbr?: string,
        public businessAssociateId?: number,
        public mailAddressId?: number,
        public deliveryAddressId?: number,
        public contactId?: number
    ) {}
}
