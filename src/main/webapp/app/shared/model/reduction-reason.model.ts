export interface IReductionReason {
    id?: number;
    reason?: string;
    proprieteryReasonCode?: string;
    businessUnit?: string;
}

export class ReductionReason implements IReductionReason {
    constructor(public id?: number, public reason?: string, public proprieteryReasonCode?: string, public businessUnit?: string) {}
}
