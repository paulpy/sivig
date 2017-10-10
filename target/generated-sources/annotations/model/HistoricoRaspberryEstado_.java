package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistoricoRaspberryEstado.class)
public abstract class HistoricoRaspberryEstado_ {

	public static volatile SingularAttribute<HistoricoRaspberryEstado, CausaCambioEstado> causaCambioEstado;
	public static volatile SingularAttribute<HistoricoRaspberryEstado, Timestamp> raesMomentoCambio;
	public static volatile SingularAttribute<HistoricoRaspberryEstado, Raspberry> raspberry;
	public static volatile SingularAttribute<HistoricoRaspberryEstado, Integer> raesIdRaspberryEstado;
	public static volatile SingularAttribute<HistoricoRaspberryEstado, EstadoEquipo> estadoEquipo;
	public static volatile SingularAttribute<HistoricoRaspberryEstado, Integer> raesIdExterno;

}

