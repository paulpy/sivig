package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EstadoEquipo.class)
public abstract class EstadoEquipo_ {

	public static volatile ListAttribute<EstadoEquipo, HistoricoRaspberryEstado> historicoRaspberryEstados;
	public static volatile ListAttribute<EstadoEquipo, HistoricoEquipoEstado> historicoEquipoEstados;
	public static volatile SingularAttribute<EstadoEquipo, Integer> eseqIdEstadoEquipo;
	public static volatile SingularAttribute<EstadoEquipo, String> eseqEstadoEquipo;

}

