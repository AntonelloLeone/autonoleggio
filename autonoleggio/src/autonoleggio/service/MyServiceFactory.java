package autonoleggio.service;

import autonoleggio.dao.MyDaoFactory;
import autonoleggio.service.automobile.AutomobileService;
import autonoleggio.service.automobile.AutomobileServiceImpl;
import autonoleggio.service.batmobile.BatmobileService;
import autonoleggio.service.batmobile.BatmobileServiceImpl;
import autonoleggio.service.cliente.ClienteService;
import autonoleggio.service.cliente.ClienteServiceImpl;
import autonoleggio.service.manager.ManagerService;
import autonoleggio.service.manager.ManagerServiceImpl;

public class MyServiceFactory {
	
	private static ClienteService clienteServiceInstance=null;
	private static AutomobileService automobileService=null;
	private static ManagerService managerService=null;
	private static BatmobileService batmobileService=null;
	
	
	public static BatmobileService getBatmobileServiceInstance() {
		if(batmobileService == null) {
			batmobileService = new BatmobileServiceImpl();
			batmobileService.setBatmobileDao(MyDaoFactory.getBatmobileDAOInstance());
		}
		return batmobileService;
	}
	
	public static ManagerService getManagerServiceInstance() {
		if(managerService == null) {
			managerService = new ManagerServiceImpl();
			managerService.setManagerDao(MyDaoFactory.getManagerDAOInstance());
		}
		return managerService;
	}
	
	
	public static ClienteService getClienteServiceInstance() {
		if(clienteServiceInstance == null) {
			clienteServiceInstance = new ClienteServiceImpl();
			clienteServiceInstance.setClienteDao(MyDaoFactory.getClienteDAOInstance());
		}
		return clienteServiceInstance;
	}

	public static AutomobileService getAutomobileServiceInstance() {
		if(automobileService == null) {
			automobileService = new AutomobileServiceImpl();
			automobileService.setAutomobileDao(MyDaoFactory.getAutomobileDAOInstance());
		}
		return automobileService;
	}
}
