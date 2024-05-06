package autonoleggio.service.batmobile;

import java.util.List;

import autonoleggio.dao.batmobile.BatmobileDAO;
import autonoleggio.model.Automobile;
import autonoleggio.model.Batmobile;

public class BatmobileServiceImpl implements BatmobileService{
	
	private BatmobileDAO batmobileDAO;

	@Override
	public void setBatmobileDao(BatmobileDAO batmobileDAO) {
		this.batmobileDAO = batmobileDAO;
		
	}

	@Override
	public List<Batmobile> lista() {
		List<Batmobile> batmobili = null;
        try {
        	batmobili = batmobileDAO.list();
        } catch (Exception e) {
            // Gestisci l'eccezione in modo appropriato
            e.printStackTrace();
        }
        return batmobili;
	}

	@Override
	public void inserisciBatmobile(Batmobile o) throws Exception {
		batmobileDAO.insert(o);
		
	}



}
