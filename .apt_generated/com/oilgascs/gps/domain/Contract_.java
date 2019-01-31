package com.oilgascs.gps.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contract.class)
public abstract class Contract_ {

	public static volatile SetAttribute<Contract, ContrLoc> contrLocs;
	public static volatile SingularAttribute<Contract, String> businessUnit;
	public static volatile SingularAttribute<Contract, BusinessAssociate> busAssoc;
	public static volatile SingularAttribute<Contract, Long> id;
	public static volatile SingularAttribute<Contract, String> contrId;
	public static volatile SingularAttribute<Contract, ZonedDateTime> updateTimeStamp;
	public static volatile SingularAttribute<Contract, RateSched> rtSched;
	public static volatile SingularAttribute<Contract, String> status;
	public static volatile SingularAttribute<Contract, String> updater;

}

