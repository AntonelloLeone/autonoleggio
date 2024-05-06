package autonoleggio.service.cliente;

import java.util.List;

import autonoleggio.dao.cliente.ClienteDAO;
import autonoleggio.model.Cliente;

public interface ClienteService {
	
	public void setClienteDao(ClienteDAO clienteDAO);
	
	//
	
    List<Cliente> lista() ;
	
    Cliente caricaSingoloCliente(String text);
	
	void inserisciCliente(Cliente o) throws Exception;
	
	void eliminaCliente(Cliente o);
	
	//
	void noleggiaAuto(Cliente o);
	void consegnaAuto(Cliente o);
	List<Cliente> dammiCLientiConVeicolo();
	
}
