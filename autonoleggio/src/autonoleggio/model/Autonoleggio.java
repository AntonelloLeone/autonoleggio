package autonoleggio.model;

import java.util.ArrayList;
import java.util.List;

public class Autonoleggio {

	List<Automobile> automobili = new ArrayList<>();
	List<Batmobile> batmobili = new ArrayList<>();
	List<Cliente> clienti = new ArrayList<>();
	List<Manager> managers = new ArrayList<>();
	//add file di access con le coppie user - passoword
	Cliente clienteActive;
	private static Autonoleggio instance = null;

	// Singleton
	private Autonoleggio() {
		super();
	}

	public static Autonoleggio getInstance() {
		if (instance == null) {
			instance = new Autonoleggio();
		}
		return instance;
	}


	// control IN - con il ritorno gestisco il flow----------------------
	public int addAutomobile(Automobile automobile) {
		for (Automobile automobileItem : automobili) {
			if(automobile.getTarga().equals(automobileItem.getTarga())) {
				return 0;
			}
		}
		automobili.add(automobile);
		return 1;
	}
	
	public int addBatmobile(Batmobile batmobile) {
		for (Batmobile batmobileItem : batmobili) {
			if(batmobile.getTarga().equals(batmobileItem.getTarga())) {
				return 0;
			}
		}
		batmobili.add(batmobile);
		return 1;
	}
	
	public int addManager(Manager manager) {
		for (Manager managerItem : managers) {
			if(manager.equals(managerItem)) {
				return 0;
			}
		}
		managers.add(manager);
		return 1;
	}
	
	public int addCliente(Cliente cliente) {
		for (Cliente clienteItem : clienti) {
			if(cliente.equals(clienteItem)) {
				return 0;
			}
		}
		clienti.add(cliente);
		return 1;
	}
	
	// getter setter----------------------------------------------------
	public List<Automobile> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(List<Automobile> automobili) {
		this.automobili = automobili;
	}

	public List<Batmobile> getBatmobili() {
		return batmobili;
	}

	public void setBatmobili(List<Batmobile> batmobili) {
		this.batmobili = batmobili;
	}

	public List<Cliente> getClienti() {
		return clienti;
	}

	public void setClienti(List<Cliente> clienti) {
		this.clienti = clienti;
	}

	public List<Manager> getManagers() {
		return managers;
	}

	public void setManagers(List<Manager> manager) {
		this.managers = manager;
	}

	
	
	// util
	public Cliente getClienteActive() {
		return clienteActive;
	}

	public void setClienteActive(Cliente clienteActive) {
		this.clienteActive = clienteActive;
	}
	
}
