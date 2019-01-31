package com.oilgascs.gps.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contact.class)
public abstract class Contact_ {

	public static volatile SingularAttribute<Contact, String> firstName;
	public static volatile SingularAttribute<Contact, String> lastName;
	public static volatile SingularAttribute<Contact, User> loginId;
	public static volatile SingularAttribute<Contact, BusinessAssociate> employedBy;
	public static volatile SingularAttribute<Contact, Long> id;
	public static volatile SingularAttribute<Contact, ZonedDateTime> updateTimestamp;
	public static volatile SingularAttribute<Contact, String> updater;

}

