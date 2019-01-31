package com.oilgascs.gps.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RateSchedVald.class)
public abstract class RateSchedVald_ {

	public static volatile SingularAttribute<RateSchedVald, String> validType;
	public static volatile SingularAttribute<RateSchedVald, String> businessUnit;
	public static volatile SingularAttribute<RateSchedVald, RateSched> rateSchd;
	public static volatile SingularAttribute<RateSchedVald, Long> id;
	public static volatile SingularAttribute<RateSchedVald, ZonedDateTime> updateTimeStamp;
	public static volatile SingularAttribute<RateSchedVald, String> updater;

}

