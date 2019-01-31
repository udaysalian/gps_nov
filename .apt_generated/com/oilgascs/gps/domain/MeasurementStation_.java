package com.oilgascs.gps.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MeasurementStation.class)
public abstract class MeasurementStation_ {

	public static volatile SingularAttribute<MeasurementStation, String> locationNbr;
	public static volatile SingularAttribute<MeasurementStation, String> businessUnit;
	public static volatile SingularAttribute<MeasurementStation, Double> milepostNbr;
	public static volatile SingularAttribute<MeasurementStation, Long> id;
	public static volatile SingularAttribute<MeasurementStation, String> upstreamPipeNode;
	public static volatile SingularAttribute<MeasurementStation, ZonedDateTime> updateTimestamp;
	public static volatile SingularAttribute<MeasurementStation, String> downStreamPipeNode;
	public static volatile SingularAttribute<MeasurementStation, String> updater;

}

