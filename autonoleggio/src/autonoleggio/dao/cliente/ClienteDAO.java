package autonoleggio.dao.cliente;

import java.util.List;

import autonoleggio.dao.IBaseDao;
import autonoleggio.model.Automobile;
import autonoleggio.model.Cliente;

public interface ClienteDAO extends IBaseDao<Cliente>{
	
	// se mi servisse contattarli
	List<Cliente> findAllClientWithCar();
	void noleggia(Cliente o);
	void consegna(Cliente o);

}
