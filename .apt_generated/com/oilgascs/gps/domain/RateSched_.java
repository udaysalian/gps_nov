package com.oilgascs.gps.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RateSched.class)
public abstract class RateSched_ {

	public static volatile SingularAttribute<RateSched, String> businessUnit;
	public static volatile SingularAttribute<RateSched, String> rateScheduleCD;
	public static volatile SetAttribute<RateSched, RateSchedVald> rtSchedValds;
	public static volatile SingularAttribute<RateSched, Long> id;
	public static volatile SingularAttribute<RateSched, ZonedDateTime> updateTimeStamp;
	public static volatile SingularAttribute<RateSched, String> rsType;
	public static volatile SingularAttribute<RateSched, String> updater;

}

