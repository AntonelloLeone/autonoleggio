package autonoleggio.service.manager;

import java.util.List;

import autonoleggio.dao.manager.ManagerDAO;
import autonoleggio.model.Automobile;
import autonoleggio.model.Manager;

public class ManagerServiceImpl implements ManagerService{

	private ManagerDAO managerDAO;

	@Override
	public void setManagerDao(ManagerDAO managerDAO) {
		this.managerDAO=managerDAO;
		
	}

	@Override
	public List<Manager> lista(){
		List<Manager> managers = null;
        try {
        	managers = managerDAO.list();
        } catch (Exception e) {
            // Gestisci l'eccezione in modo appropriato
            e.printStackTrace();
        }
        return managers;
    }


	@Override
	public void inserisciManager(Manager o) throws Exception {
		managerDAO.insert(o);
		
	}
}
