package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistoricoEquipoEstado.class)
public abstract class HistoricoEquipoEstado_ {

	public static volatile SingularAttribute<HistoricoEquipoEstado, Equipo> equipo;
	public static volatile SingularAttribute<HistoricoEquipoEstado, CausaCambioEstado> causaCambioEstado;
	public static volatile SingularAttribute<HistoricoEquipoEstado, Integer> eqesIdExterno;
	public static volatile SingularAttribute<HistoricoEquipoEstado, Timestamp> eqesMomentoEstado;
	public static volatile SingularAttribute<HistoricoEquipoEstado, EstadoEquipo> estadoEquipo;
	public static volatile SingularAttribute<HistoricoEquipoEstado, Integer> eqesIdHistoricoEstadoReloj;

}

