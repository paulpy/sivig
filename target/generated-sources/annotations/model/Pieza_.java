package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pieza.class)
public abstract class Pieza_ {

	public static volatile ListAttribute<Pieza, CambioPieza> cambioPiezas;
	public static volatile SingularAttribute<Pieza, Date> piezUltimaActualizacion;
	public static volatile SingularAttribute<Pieza, Integer> piezIdPieza;
	public static volatile SingularAttribute<Pieza, Integer> piezCantidad;
	public static volatile SingularAttribute<Pieza, String> piezDetalle;
	public static volatile SingularAttribute<Pieza, String> piezPieza;

}

