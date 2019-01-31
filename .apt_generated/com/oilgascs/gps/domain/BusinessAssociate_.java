package com.oilgascs.gps.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BusinessAssociate.class)
public abstract class BusinessAssociate_ {

	public static volatile SingularAttribute<BusinessAssociate, String> baAbbr;
	public static volatile SetAttribute<BusinessAssociate, BusinessAssociateAddress> businessAssociateAddresses;
	public static volatile SingularAttribute<BusinessAssociate, Long> id;
	public static volatile SingularAttribute<BusinessAssociate, String> baName;
	public static volatile SetAttribute<BusinessAssociate, Contract> contracts;
	public static volatile SingularAttribute<BusinessAssociate, String> baNbr;
	public static volatile SingularAttribute<BusinessAssociate, String> baDunsNbr;

}

