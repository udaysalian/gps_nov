package com.oilgascs.gps.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BusinessUnit.class)
public abstract class BusinessUnit_ {

	public static volatile SingularAttribute<BusinessUnit, String> ediPipeId;
	public static volatile SingularAttribute<BusinessUnit, String> businessUnit;
	public static volatile SingularAttribute<BusinessUnit, byte[]> companyLogo;
	public static volatile SingularAttribute<BusinessUnit, Long> id;
	public static volatile SingularAttribute<BusinessUnit, String> companyLogoContentType;
	public static volatile SingularAttribute<BusinessUnit, String> businessAssociateNbr;
	public static volatile SingularAttribute<BusinessUnit, ZonedDateTime> updateTimestamp;
	public static volatile SingularAttribute<BusinessUnit, String> updater;

}

