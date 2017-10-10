package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Mantenimiento.class)
public abstract class Mantenimiento_ {

	public static volatile SingularAttribute<Mantenimiento, Integer> mantIdMantenimiento;
	public static volatile SingularAttribute<Mantenimiento, String> mantObservacion;
	public static volatile SingularAttribute<Mantenimiento, Date> mantFechaHora;
	public static volatile SingularAttribute<Mantenimiento, Dependencia> dependencia;
	public static volatile SingularAttribute<Mantenimiento, Funcionario> funcionario;
	public static volatile SingularAttribute<Mantenimiento, Contrato> contrato;

}

