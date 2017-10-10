package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Direccion.class)
public abstract class Direccion_ {

	public static volatile SingularAttribute<Direccion, Integer> direIdDireccion;
	public static volatile SingularAttribute<Direccion, Ciudad> ciudad;
	public static volatile SingularAttribute<Direccion, Integer> direNro;
	public static volatile SingularAttribute<Direccion, Calle> calle3;
	public static volatile ListAttribute<Direccion, Dependencia> dependencias;
	public static volatile SingularAttribute<Direccion, Calle> calle1;
	public static volatile SingularAttribute<Direccion, Calle> calle2;

}

