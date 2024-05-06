package autonoleggio.dao.cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import autonoleggio.dbms.Dbms;
import autonoleggio.eccezioni.MyValidationEx;
import autonoleggio.model.Automobile;
import autonoleggio.model.Autonoleggio;
import autonoleggio.model.Cliente;
import autonoleggio.model.Manager;
import autonoleggio.model.Role;
import autonoleggio.model.Sesso;
import autonoleggio.validazioni.Validation;

public class ClienteDAOimpl implements ClienteDAO{

	//per semplificare
	private Autonoleggio autonoleggio = Autonoleggio.getInstance();
	
	
	@Override
	public List<Cliente> list() throws Exception {
		List<Cliente> clienti = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(Dbms.filenameClienti))) {
			String line;
			while ((line = br.readLine()) != null) {
				// devo controllare lunghezza array
				// correttamente altrimenti eccezione

				String[] parts = line.split(",");

				Cliente cliente = new Cliente();
				

				if (parts.length == 13) {
					// valido i campi
					try {
						 String nome= parts[0].trim();
						 String cognome= parts[1].trim();
						 String email=Validation.checkMail(parts[2].trim());
						 String codiceFiscale=Validation.checkCF(parts[3].trim());
						 Sesso sesso=Validation.checkSesso(parts[4].trim());
						 String luogoDiNascita=parts[5].trim();
						 String provincia=parts[6].trim();
						 LocalDate dataDiNascita=Validation.checkDate(parts[7].trim());
						 Role ruolo=Validation.checkRuolo(parts[8].trim());
						 String userName=Validation.checkUserName(parts[9].trim());
						 
						 String password=Validation.checkPassword(parts[10].trim());
						 String patente=Validation.checkPatente(parts[11].trim());
						 String targa="null";
						 if(!parts[12].trim().equals("null")) {
							  targa=Validation.checkTarga(parts[12].trim());	
						 }
						 
						// popolo obj
						 cliente.setNome(nome);
						 cliente.setCognome(cognome);
						 cliente.setEmail(email);
						 cliente.setCodiceFiscale(codiceFiscale);
						 cliente.setSesso(sesso);
						 cliente.setLuogoDiNascita(luogoDiNascita);
						 cliente.setProvincia(provincia);
						 cliente.setDataDiNascita(dataDiNascita);
						 cliente.setRuolo(ruolo);
						 cliente.setUserName(userName);
						 cliente.setPassword(password);
						 cliente.setPatente(patente);
						 cliente.setAutomobile(null);
						 cliente.setTarga(targa);
					} catch (MyValidationEx | NumberFormatException e) {
						System.out.println(e.getMessage());
						throw new Exception("errore nell'acquisizione di uno degli elementi di clienti.txt");
					}

				} else {
					throw new Exception("errore nel numero degli elementi di clienti.txt");
				}
				
				clienti.add(cliente);
				
				
			}
		} catch (IOException e) {
			System.out.println("il file clienti.txt non esisteva, ora c'è");
			try (FileWriter writer = new FileWriter(Dbms.filenameClienti)) {
			    // Vuoto -crea
			} catch (IOException eX) {
			    System.out.println("Qualcosa è andato storto nella creazione del file cliente");
			}
		}
		autonoleggio.setClienti(clienti);
		// check if cliente has car
		for(Cliente clienteItem:autonoleggio.getClienti()) {
			if(clienteItem.getTarga()!=null) {
				for(Automobile auto:autonoleggio.getAutomobili()) {
					if(auto.getTarga().equals(clienteItem.getTarga())) {
						clienteItem.setAutomobile(auto);
					}
				}
			}
		}
		return clienti;
	}

	@Override
	public Cliente get(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Cliente o) throws Exception {
		// attenzione a controllo null ed eccezioni
		if (o == null) {
			throw new Exception("null client?");
		}
		for (Cliente cliente : autonoleggio.getClienti()) {
			if (cliente.equals(o)) {
				throw new Exception("cliente già presente!");
			}
		}
		// backend
				autonoleggio.addCliente(o);
				// ripristino db - attenzione a date null
				try (FileWriter writer = new FileWriter(Dbms.filenameClienti)) {
					for (Cliente cliente : autonoleggio.getClienti()) {
						writer.write(cliente.getNome() + "," + cliente.getCognome() + "," + cliente.getEmail() + ","
								+ cliente.getCodiceFiscale() + "," + cliente.getSesso() + "," + cliente.getLuogoDiNascita()
								+ "," + cliente.getProvincia() + "," + cliente.getDataDiNascita() + "," + cliente.getRuolo()
								+ "," + cliente.getUserName() + "," + cliente.getPassword() +"," +cliente.getPatente()+"," +
								cliente.getTarga()+"\n");
					}
				} catch (IOException e) {
					System.out.println("qualcosa andato storto nella insert di cliente");
				}
	}

	@Override
	public void delete(Cliente o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cliente> findAllClientWithCar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Cliente o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noleggia(Cliente o) {
		try (FileWriter writer = new FileWriter(Dbms.filenameClienti)) {
			for (Cliente cliente : autonoleggio.getClienti()) {
				writer.write(cliente.getNome() + "," + cliente.getCognome() + "," + cliente.getEmail() + ","
						+ cliente.getCodiceFiscale() + "," + cliente.getSesso() + "," + cliente.getLuogoDiNascita()
						+ "," + cliente.getProvincia() + "," + cliente.getDataDiNascita() + "," + cliente.getRuolo()
						+ "," + cliente.getUserName() + "," + cliente.getPassword() +"," +cliente.getPatente()+"," +
						cliente.getTarga()+"\n");
			}
		} catch (IOException e) {
			System.out.println("qualcosa andato storto nel noleggio - cliente");
		}
		
	}

	@Override
	public void consegna(Cliente o) {
		try (FileWriter writer = new FileWriter(Dbms.filenameClienti)) {
			for (Cliente cliente : autonoleggio.getClienti()) {
				writer.write(cliente.getNome() + "," + cliente.getCognome() + "," + cliente.getEmail() + ","
						+ cliente.getCodiceFiscale() + "," + cliente.getSesso() + "," + cliente.getLuogoDiNascita()
						+ "," + cliente.getProvincia() + "," + cliente.getDataDiNascita() + "," + cliente.getRuolo()
						+ "," + cliente.getUserName() + "," + cliente.getPassword() +"," +cliente.getPatente()+"," +
						cliente.getTarga()+"\n");
			}
		} catch (IOException e) {
			System.out.println("qualcosa andato storto nella insert di cliente");
		}
		
	}

}
