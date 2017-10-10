package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dependencia.class)
public abstract class Dependencia_ {

	public static volatile ListAttribute<Dependencia, Instalacion> instalacions;
	public static volatile SingularAttribute<Dependencia, Direccion> direccion;
	public static volatile ListAttribute<Dependencia, Mantenimiento> mantenimientos;
	public static volatile SingularAttribute<Dependencia, Entidad> entidad;
	public static volatile SingularAttribute<Dependencia, Boolean> depeEstado;
	public static volatile SingularAttribute<Dependencia, Integer> depeIdDependencia;
	public static volatile SingularAttribute<Dependencia, String> depeNombreDependencia;

}

