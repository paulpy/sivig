package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contrato.class)
public abstract class Contrato_ {

	public static volatile ListAttribute<Contrato, Mantenimiento> mantenimientos;
	public static volatile SingularAttribute<Contrato, Integer> contIdContrato;
	public static volatile SingularAttribute<Contrato, Date> contFechaInicio;
	public static volatile SingularAttribute<Contrato, String> contIdentificadorContrato;
	public static volatile SingularAttribute<Contrato, Entidad> entidad;
	public static volatile SingularAttribute<Contrato, Funcionario> funcionario;
	public static volatile SingularAttribute<Contrato, String> contObservacion;
	public static volatile SingularAttribute<Contrato, Date> contFechaFinalizacion;
	public static volatile SingularAttribute<Contrato, Boolean> contActivo;

}

