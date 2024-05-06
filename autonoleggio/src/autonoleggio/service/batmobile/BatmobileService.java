package autonoleggio.service.batmobile;

import java.util.List;

import autonoleggio.dao.batmobile.BatmobileDAO;
import autonoleggio.model.Batmobile;

public interface BatmobileService {
	
	public void setBatmobileDao(BatmobileDAO batmobileDAO);

	List<Batmobile> lista();

	void inserisciBatmobile(Batmobile o) throws Exception;
}
