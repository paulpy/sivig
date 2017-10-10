package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Entidad.class)
public abstract class Entidad_ {

	public static volatile SingularAttribute<Entidad, Boolean> entiEstado;
	public static volatile ListAttribute<Entidad, Dependencia> dependencias;
	public static volatile SingularAttribute<Entidad, String> entiEntidad;
	public static volatile SingularAttribute<Entidad, Integer> entiIdEntidad;
	public static volatile ListAttribute<Entidad, Contrato> contratos;

}

