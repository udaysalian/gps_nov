package com.oilgascs.gps.domain;

import com.oilgascs.gps.domain.enumeration.NomRequestStatus;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Nomination.class)
public abstract class Nomination_ {

	public static volatile SingularAttribute<Nomination, Integer> scheduledRcptQty;
	public static volatile SingularAttribute<Nomination, String> businessUnit;
	public static volatile SingularAttribute<Nomination, Integer> reqestedDlvryQty;
	public static volatile SingularAttribute<Nomination, Activity> activity;
	public static volatile SingularAttribute<Nomination, Contract> contract;
	public static volatile SingularAttribute<Nomination, ZonedDateTime> updateTimeStamp;
	public static volatile SingularAttribute<Nomination, String> updater;
	public static volatile SetAttribute<Nomination, NominationPriority> priorities;
	public static volatile SingularAttribute<Nomination, Integer> requestedRcptQty;
	public static volatile SingularAttribute<Nomination, Integer> scheduledDlvryQty;
	public static volatile SingularAttribute<Nomination, Long> id;
	public static volatile SingularAttribute<Nomination, LocalDate> gasDate;
	public static volatile SingularAttribute<Nomination, NomRequestStatus> requestStatus;

}

