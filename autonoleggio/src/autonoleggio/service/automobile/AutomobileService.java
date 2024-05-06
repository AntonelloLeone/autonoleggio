package autonoleggio.service.automobile;

import java.util.List;

import autonoleggio.dao.automobile.AutomobileDAO;
import autonoleggio.model.Automobile;
import autonoleggio.model.Cliente;

public interface AutomobileService {

	public void setAutomobileDao(AutomobileDAO automobileDAO);

	List<Automobile> lista();

	void inserisciAutomobile(Automobile o) throws Exception;

	void eliminaAutomoble(Automobile o) ;
	void noleggiaAutomobile(Automobile o);

	//
	List<Automobile> dammiDisponibili();
	void consegnaAuto(Automobile o);
	List<Automobile> trovaByName(String s) ;
	List<Automobile> trovaByPrice(int i) ;
}
