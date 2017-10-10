package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Raspberry.class)
public abstract class Raspberry_ {

	public static volatile ListAttribute<Raspberry, HistoricoRaspberryEstado> historicoRaspberryEstados;
	public static volatile SingularAttribute<Raspberry, Date> raspFechaIngreso;
	public static volatile SingularAttribute<Raspberry, String> raspIp;
	public static volatile ListAttribute<Raspberry, Equipo> equipos;
	public static volatile SingularAttribute<Raspberry, Boolean> raspActivo;
	public static volatile SingularAttribute<Raspberry, Integer> raspIdRaspberry;
	public static volatile SingularAttribute<Raspberry, String> raspEstado;
	public static volatile SingularAttribute<Raspberry, String> raspNombre;

}

