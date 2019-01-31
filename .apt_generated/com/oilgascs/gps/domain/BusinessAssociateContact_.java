package com.oilgascs.gps.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BusinessAssociateContact.class)
public abstract class BusinessAssociateContact_ {

	public static volatile SingularAttribute<BusinessAssociateContact, LocalDate> beginDate;
	public static volatile SingularAttribute<BusinessAssociateContact, LocalDate> endDate;
	public static volatile SingularAttribute<BusinessAssociateContact, BusinessAssociateAddress> deliveryAddress;
	public static volatile SingularAttribute<BusinessAssociateContact, BusinessAssociate> businessAssociate;
	public static volatile SingularAttribute<BusinessAssociateContact, Contact> contact;
	public static volatile SingularAttribute<BusinessAssociateContact, BusinessAssociateAddress> mailAddress;
	public static volatile SingularAttribute<BusinessAssociateContact, Long> id;

}

