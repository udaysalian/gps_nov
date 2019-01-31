package com.oilgascs.gps.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContrLoc.class)
public abstract class ContrLoc_ {

	public static volatile SingularAttribute<ContrLoc, Integer> quantity;
	public static volatile SingularAttribute<ContrLoc, String> businessUnit;
	public static volatile SingularAttribute<ContrLoc, LocalDate> effectiveToDate;
	public static volatile SingularAttribute<ContrLoc, Contract> contract;
	public static volatile SingularAttribute<ContrLoc, MeasurementStation> location;
	public static volatile SingularAttribute<ContrLoc, Long> id;
	public static volatile SingularAttribute<ContrLoc, LocalDate> effectiveFromDate;
	public static volatile SingularAttribute<ContrLoc, String> type;
	public static volatile SingularAttribute<ContrLoc, ZonedDateTime> updateTimeStamp;
	public static volatile SingularAttribute<ContrLoc, String> updater;

}

