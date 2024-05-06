package autonoleggio.service.automobile;

import java.util.List;

import autonoleggio.dao.automobile.AutomobileDAO;
import autonoleggio.model.Automobile;
import autonoleggio.model.Cliente;

public class AutomobileServiceImpl implements AutomobileService {

	private AutomobileDAO automobileDAO;

	@Override
	public void setAutomobileDao(AutomobileDAO automobileDAO) {
		this.automobileDAO = automobileDAO;

	}

	@Override
    public List<Automobile> lista() {
        List<Automobile> automobili = null;
        try {
            automobili = automobileDAO.list();
        } catch (Exception e) {
            // Gestisci l'eccezione in modo appropriato
            e.printStackTrace();
        }
        return automobili;
    }

	@Override
	public void inserisciAutomobile(Automobile o) throws Exception {
		automobileDAO.insert(o);

	}

	@Override
	public void eliminaAutomoble(Automobile o)  {
		automobileDAO.delete(o);

	}

	@Override
	public List<Automobile> dammiDisponibili() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void noleggiaAutomobile(Automobile o)  {
		automobileDAO.noleggia(o);
		
	}

	@Override
	public void consegnaAuto(Automobile o) {
		automobileDAO.consegna(o);
		
	}

	@Override
	public List<Automobile> trovaByName(String s) {
		
		return automobileDAO.findByName(s);
	}

	@Override
	public List<Automobile> trovaByPrice(int i) {
		// TODO Auto-generated method stub
		return automobileDAO.findByPrice(i);
	}

}
