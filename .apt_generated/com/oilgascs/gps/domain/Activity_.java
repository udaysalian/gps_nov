package com.oilgascs.gps.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Activity.class)
public abstract class Activity_ {

	public static volatile SingularAttribute<Activity, Contract> downstreamContract;
	public static volatile SingularAttribute<Activity, String> businessUnit;
	public static volatile SingularAttribute<Activity, MeasurementStation> deliveryLocation;
	public static volatile SingularAttribute<Activity, Contract> upstreamContract;
	public static volatile SingularAttribute<Activity, String> contrId;
	public static volatile SingularAttribute<Activity, ZonedDateTime> updateTimeStamp;
	public static volatile SingularAttribute<Activity, RateSched> rtSched;
	public static volatile SingularAttribute<Activity, String> updater;
	public static volatile SingularAttribute<Activity, String> transactionType;
	public static volatile SingularAttribute<Activity, BusinessAssociate> downstreamBA;
	public static volatile SingularAttribute<Activity, BusinessAssociate> upstreamBA;
	public static volatile SingularAttribute<Activity, Long> id;
	public static volatile SingularAttribute<Activity, String> activityNbr;
	public static volatile SingularAttribute<Activity, MeasurementStation> receiptLocation;

}

