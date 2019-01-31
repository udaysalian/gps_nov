package com.oilgascs.gps.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BusinessAssociateAddress.class)
public abstract class BusinessAssociateAddress_ {

	public static volatile SingularAttribute<BusinessAssociateAddress, String> zipCode;
	public static volatile SingularAttribute<BusinessAssociateAddress, String> addressNbr;
	public static volatile SingularAttribute<BusinessAssociateAddress, String> city;
	public static volatile SingularAttribute<BusinessAssociateAddress, BusinessAssociate> businessAssociate;
	public static volatile SingularAttribute<BusinessAssociateAddress, String> addLine1;
	public static volatile SingularAttribute<BusinessAssociateAddress, Long> id;
	public static volatile SingularAttribute<BusinessAssociateAddress, String> addLine2;
	public static volatile SingularAttribute<BusinessAssociateAddress, String> state;

}

