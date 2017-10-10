package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Auditoria.class)
public abstract class Auditoria_ {

	public static volatile SingularAttribute<Auditoria, Timestamp> audiMomentoAccion;
	public static volatile SingularAttribute<Auditoria, Usuario> usuario;
	public static volatile SingularAttribute<Auditoria, String> audiDetalleAccion;
	public static volatile SingularAttribute<Auditoria, Integer> audiIdAuditoria;
	public static volatile SingularAttribute<Auditoria, String> audiTabla;

}

