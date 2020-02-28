package modelo.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import modelo.beans.Cuenta;

public abstract class CuentaDAO extends AbstractDAOImpl<Cuenta, Integer>{
	
	public abstract boolean anhadirMonto(Cuenta cuenta, double doublee);
	public abstract boolean retirarMonto(Cuenta cuenta, double doublee);
	public abstract boolean transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto);

}
