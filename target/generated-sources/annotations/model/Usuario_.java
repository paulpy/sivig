package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> usuaUsuario;
	public static volatile SingularAttribute<Usuario, String> usuaPass;
	public static volatile ListAttribute<Usuario, Auditoria> auditorias;
	public static volatile SingularAttribute<Usuario, Boolean> usuaActivo;
	public static volatile SingularAttribute<Usuario, Funcionario> funcionario;
	public static volatile SingularAttribute<Usuario, String> usuaTipo;
	public static volatile SingularAttribute<Usuario, Integer> usuaIdUsuario;

}

