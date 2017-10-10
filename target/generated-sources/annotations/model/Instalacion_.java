package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Instalacion.class)
public abstract class Instalacion_ {

	public static volatile SingularAttribute<Instalacion, Equipo> equipo;
	public static volatile SingularAttribute<Instalacion, Dependencia> dependencia;
	public static volatile SingularAttribute<Instalacion, String> instObservacion;
	public static volatile SingularAttribute<Instalacion, Funcionario> funcionario;
	public static volatile SingularAttribute<Instalacion, Integer> instIdInstalacion;
	public static volatile SingularAttribute<Instalacion, Date> instFechaInstalacion;

}

