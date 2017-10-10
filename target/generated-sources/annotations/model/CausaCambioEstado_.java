package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CausaCambioEstado.class)
public abstract class CausaCambioEstado_ {

	public static volatile ListAttribute<CausaCambioEstado, HistoricoRaspberryEstado> historicoRaspberryEstados;
	public static volatile SingularAttribute<CausaCambioEstado, Integer> ccesIdCausaCambio;
	public static volatile ListAttribute<CausaCambioEstado, HistoricoEquipoEstado> historicoEquipoEstados;
	public static volatile SingularAttribute<CausaCambioEstado, String> ccesCausaEstado;

}

