package modelo.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import modelo.beans.Cuenta;

public class CuentaDAOImpl extends CuentaDAO {

	public CuentaDAOImpl() {
		super();
	}

	@Override
	public int insert(Cuenta entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Cuenta entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer clave) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cuenta findById(Integer clave) {
		sql = "select c from Cuenta c where c.numeroCuenta = :numC";

		try {
			query = em.createQuery(sql);
			query.setParameter("numC", clave);
			return (Cuenta) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Cuenta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean anhadirMonto(Cuenta cuenta, double doublee) {
		try {
			cuenta.setSaldo(cuenta.getSaldo() + doublee);
			tx.begin();
			em.merge(cuenta);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	@Override
	public boolean retirarMonto(Cuenta cuenta, double doublee) {
		// TODO Auto-generated method stub
		try {
			cuenta.setSaldo(cuenta.getSaldo() - doublee);
			tx.begin();
			em.merge(cuenta);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	@Override
	public boolean transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto) {
		return anhadirMonto(cuentaDestino, monto) && retirarMonto(cuentaOrigen, monto);
	}

}
