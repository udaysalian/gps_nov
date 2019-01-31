package com.oilgascs.gps.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NominationPriority.class)
public abstract class NominationPriority_ {

	public static volatile SingularAttribute<NominationPriority, String> businessUnit;
	public static volatile SingularAttribute<NominationPriority, Activity> activity;
	public static volatile SingularAttribute<NominationPriority, Integer> oldQty;
	public static volatile SingularAttribute<NominationPriority, Contract> contract;
	public static volatile SingularAttribute<NominationPriority, Integer> newQty;
	public static volatile SingularAttribute<NominationPriority, ZonedDateTime> updateTimeStamp;
	public static volatile SingularAttribute<NominationPriority, String> updater;
	public static volatile SingularAttribute<NominationPriority, Nomination> nomination;
	public static volatile SingularAttribute<NominationPriority, String> prtyTp;
	public static volatile SingularAttribute<NominationPriority, String> dirOfFlow;
	public static volatile SingularAttribute<NominationPriority, String> subType;
	public static volatile SingularAttribute<NominationPriority, Long> id;
	public static volatile SingularAttribute<NominationPriority, LocalDate> gasDate;

}

