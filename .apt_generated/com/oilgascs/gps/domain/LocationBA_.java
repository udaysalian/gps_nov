package com.oilgascs.gps.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LocationBA.class)
public abstract class LocationBA_ {

	public static volatile SingularAttribute<LocationBA, String> locationNbr;
	public static volatile SingularAttribute<LocationBA, String> businessUnit;
	public static volatile SingularAttribute<LocationBA, BusinessAssociate> busAssoc;
	public static volatile SingularAttribute<LocationBA, Long> id;
	public static volatile SingularAttribute<LocationBA, String> locationBAType;
	public static volatile SingularAttribute<LocationBA, ZonedDateTime> updateTimestamp;
	public static volatile SingularAttribute<LocationBA, String> updater;

}

