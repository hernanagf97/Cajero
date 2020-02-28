package modelo.daos;

import java.util.List;

public interface CrudGenerico<E, K> {
	
	public int insert(E entidad);
	public int update(E entidad);
	public int delete(K clave);
	public E findById(K clave);
	public List<E> findAll();

}
