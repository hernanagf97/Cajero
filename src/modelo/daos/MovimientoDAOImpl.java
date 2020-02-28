package modelo.daos;

import java.util.List;

import modelo.beans.Cuenta;
import modelo.beans.Movimiento;

public class MovimientoDAOImpl extends MovimientoDAO{

	@Override
	public int insert(Movimiento entidad) {
		tx.begin();
		em.persist(entidad);
		tx.commit();
		return 0;
	}

	@Override
	public int update(Movimiento entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer clave) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Movimiento findById(Integer clave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movimiento> findAll() {
		
		return null;
	}
	
	public List<Movimiento> findPorCuenta(Cuenta numCuenta) {
		sql ="Select m from Movimiento m where m.cuenta = :cuen";
		query = em.createQuery(sql);
		query.setParameter("cuen", numCuenta);
		return query.getResultList();}


}
