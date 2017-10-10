package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CambioPieza.class)
public abstract class CambioPieza_ {

	public static volatile SingularAttribute<CambioPieza, Equipo> equipo;
	public static volatile SingularAttribute<CambioPieza, String> capiObservacion;
	public static volatile SingularAttribute<CambioPieza, Pieza> pieza;
	public static volatile SingularAttribute<CambioPieza, Funcionario> funcionario;
	public static volatile SingularAttribute<CambioPieza, Date> capiFechaCambio;
	public static volatile SingularAttribute<CambioPieza, Integer> capiIdCambioPieza;

}

