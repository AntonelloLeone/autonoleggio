package autonoleggio.service.cliente;

import java.util.List;

import autonoleggio.dao.cliente.ClienteDAO;
import autonoleggio.model.Cliente;
import autonoleggio.model.Manager;

public class ClienteServiceImpl implements ClienteService{

	private ClienteDAO clienteDAO;

	@Override
	public void setClienteDao(ClienteDAO clienteDAO) {
		this.clienteDAO=clienteDAO;
		
	}

	@Override
	public List<Cliente> lista() {
		List<Cliente> clienti = null;
        try {
        	clienti = clienteDAO.list();
        } catch (Exception e) {
            // Gestisci l'eccezione in modo appropriato
            e.printStackTrace();
        }
        return clienti;
		
	}

	@Override
	public Cliente caricaSingoloCliente(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserisciCliente(Cliente o) throws Exception {
		clienteDAO.insert(o);
		
	}

	@Override
	public void eliminaCliente(Cliente o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cliente> dammiCLientiConVeicolo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void noleggiaAuto(Cliente o) {
		clienteDAO.noleggia(o);
		
	}

	@Override
	public void consegnaAuto(Cliente o) {
		clienteDAO.consegna(o);
		
	}
}
