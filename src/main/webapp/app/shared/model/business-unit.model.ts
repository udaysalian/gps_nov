import { Moment } from 'moment';

export interface IBusinessUnit {
    id?: number;
    businessUnit?: string;
    businessAssociateNbr?: string;
    ediPipeId?: string;
    companyLogoContentType?: string;
    companyLogo?: any;
    updater?: string;
    updateTimestamp?: Moment;
}

export class BusinessUnit implements IBusinessUnit {
    constructor(
        public id?: number,
        public businessUnit?: string,
        public businessAssociateNbr?: string,
        public ediPipeId?: string,
        public companyLogoContentType?: string,
        public companyLogo?: any,
        public updater?: string,
        public updateTimestamp?: Moment
    ) {}
}
