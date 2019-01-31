export interface IBusinessAssociateAddress {
    id?: number;
    addLine1?: string;
    addressNbr?: string;
    addLine2?: string;
    city?: string;
    state?: string;
    zipCode?: string;
    businessAssociateId?: number;
}

export class BusinessAssociateAddress implements IBusinessAssociateAddress {
    constructor(
        public id?: number,
        public addLine1?: string,
        public addressNbr?: string,
        public addLine2?: string,
        public city?: string,
        public state?: string,
        public zipCode?: string,
        public businessAssociateId?: number
    ) {}
}
