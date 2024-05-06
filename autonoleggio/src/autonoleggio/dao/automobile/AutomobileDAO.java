package autonoleggio.dao.automobile;

import java.util.List;

import autonoleggio.dao.IBaseDao;
import autonoleggio.model.Automobile;
import autonoleggio.model.Cliente;

public interface AutomobileDAO extends IBaseDao<Automobile>{
	
	List<Cliente> viewFree() ;
	void noleggia(Automobile o);
	void consegna(Automobile o);
	List<Automobile> findByName(String s) ;
	List<Automobile> findByPrice(int i) ;

}
