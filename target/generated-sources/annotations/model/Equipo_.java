package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Equipo.class)
public abstract class Equipo_ {

	public static volatile ListAttribute<Equipo, Instalacion> instalacions;
	public static volatile SingularAttribute<Equipo, String> equiSerieEquipo;
	public static volatile ListAttribute<Equipo, CambioPieza> cambioPiezas;
	public static volatile SingularAttribute<Equipo, String> equiIdentificador;
	public static volatile ListAttribute<Equipo, HistoricoEquipoEstado> historicoEquipoEstados;
	public static volatile SingularAttribute<Equipo, Raspberry> raspberry;
	public static volatile SingularAttribute<Equipo, String> equiEstado;
	public static volatile SingularAttribute<Equipo, Integer> equiIdEquipo;
	public static volatile ListAttribute<Equipo, Interaccion> interacciones;
	public static volatile SingularAttribute<Equipo, Date> equiFechaIngreso;
	public static volatile SingularAttribute<Equipo, Boolean> equiActivo;

}

