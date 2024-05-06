package autonoleggio.service.manager;

import java.util.List;

import autonoleggio.dao.manager.ManagerDAO;
import autonoleggio.model.Manager;

public interface ManagerService {
	
	public void setManagerDao(ManagerDAO managerDAO);

	List<Manager> lista();

	void inserisciManager(Manager o) throws Exception;

	
	
}
