import { Moment } from 'moment';

export interface IMeasurementStation {
    id?: number;
    locationNbr?: string;
    milepostNbr?: number;
    upstreamPipeNode?: string;
    downStreamPipeNode?: string;
    businessUnit?: string;
    updater?: string;
    updateTimestamp?: Moment;
}

export class MeasurementStation implements IMeasurementStation {
    constructor(
        public id?: number,
        public locationNbr?: string,
        public milepostNbr?: number,
        public upstreamPipeNode?: string,
        public downStreamPipeNode?: string,
        public businessUnit?: string,
        public updater?: string,
        public updateTimestamp?: Moment
    ) {}
}
