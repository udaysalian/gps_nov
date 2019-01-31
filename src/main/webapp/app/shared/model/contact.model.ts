import { Moment } from 'moment';

export interface IContact {
    id?: number;
    firstName?: string;
    lastName?: string;
    updater?: string;
    updateTimestamp?: Moment;
    loginIdLogin?: string;
    loginIdId?: number;
    employedByBaAbbr?: string;
    employedById?: number;
}

export class Contact implements IContact {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public updater?: string,
        public updateTimestamp?: Moment,
        public loginIdLogin?: string,
        public loginIdId?: number,
        public employedByBaAbbr?: string,
        public employedById?: number
    ) {}
}
