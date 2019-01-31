import { IContract } from 'app/shared/model//contract.model';
import { IBusinessAssociateAddress } from 'app/shared/model//business-associate-address.model';

export interface IBusinessAssociate {
    id?: number;
    baName?: string;
    baAbbr?: string;
    baNbr?: string;
    baDunsNbr?: string;
    contracts?: IContract[];
    businessAssociateAddresses?: IBusinessAssociateAddress[];
}

export class BusinessAssociate implements IBusinessAssociate {
    constructor(
        public id?: number,
        public baName?: string,
        public baAbbr?: string,
        public baNbr?: string,
        public baDunsNbr?: string,
        public contracts?: IContract[],
        public businessAssociateAddresses?: IBusinessAssociateAddress[]
    ) {}
}
