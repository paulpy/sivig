package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Funcionario.class)
public abstract class Funcionario_ {

	public static volatile ListAttribute<Funcionario, Instalacion> instalacions;
	public static volatile ListAttribute<Funcionario, CambioPieza> cambioPiezas;
	public static volatile ListAttribute<Funcionario, Mantenimiento> mantenimientos;
	public static volatile SingularAttribute<Funcionario, Boolean> funcEstado;
	public static volatile SingularAttribute<Funcionario, Integer> funcIdFuncionario;
	public static volatile SingularAttribute<Funcionario, String> funcCargo;
	public static volatile ListAttribute<Funcionario, Contrato> contratos;
	public static volatile SingularAttribute<Funcionario, Persona> persona;
	public static volatile SingularAttribute<Funcionario, String> funcArea;
	public static volatile ListAttribute<Funcionario, Usuario> usuarios;

}

