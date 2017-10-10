package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Persona.class)
public abstract class Persona_ {

	public static volatile SingularAttribute<Persona, String> persNombre;
	public static volatile ListAttribute<Persona, Funcionario> funcionarios;
	public static volatile SingularAttribute<Persona, String> persSexo;
	public static volatile SingularAttribute<Persona, Integer> persIdPersona;
	public static volatile SingularAttribute<Persona, String> persApellido;
	public static volatile SingularAttribute<Persona, Integer> persCi;
	public static volatile SingularAttribute<Persona, Date> persFechaNacimiento;

}

