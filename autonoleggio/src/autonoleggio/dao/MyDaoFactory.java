package autonoleggio.dao;

import autonoleggio.dao.automobile.AutomobileDAO;
import autonoleggio.dao.automobile.AutomobileDAOimpl;
import autonoleggio.dao.batmobile.BatmobileDAO;
import autonoleggio.dao.batmobile.BatmobileDAOimpl;
import autonoleggio.dao.cliente.ClienteDAO;
import autonoleggio.dao.cliente.ClienteDAOimpl;
import autonoleggio.dao.manager.ManagerDAO;
import autonoleggio.dao.manager.ManagerDAOimpl;

public class MyDaoFactory {

	private static ClienteDAO clienteDAOInstance = null;
	private static AutomobileDAO automobileDAOInstance = null;
	private static ManagerDAO managerDAOInstance = null;
	private static BatmobileDAO batmobileDAOInstance = null;
	
	
	
	public static BatmobileDAO getBatmobileDAOInstance() {
		if (batmobileDAOInstance == null) {
			batmobileDAOInstance = new BatmobileDAOimpl();

		}
		return batmobileDAOInstance;
	}

	public static ManagerDAO getManagerDAOInstance() {
		if (managerDAOInstance == null) {
			managerDAOInstance = new ManagerDAOimpl();

		}
		return managerDAOInstance;
	}

	public static ClienteDAO getClienteDAOInstance() {
		if (clienteDAOInstance == null) {
			clienteDAOInstance = new ClienteDAOimpl();

		}
		return clienteDAOInstance;
	}

	public static AutomobileDAO getAutomobileDAOInstance() {
		if (automobileDAOInstance == null) {
			automobileDAOInstance = new AutomobileDAOimpl();

		}
		return automobileDAOInstance;
	}

}
