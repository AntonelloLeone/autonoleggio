package autonoleggio.dao.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import autonoleggio.dbms.Dbms;
import autonoleggio.eccezioni.MyValidationEx;
import autonoleggio.model.Autonoleggio;
import autonoleggio.model.Manager;
import autonoleggio.model.Role;
import autonoleggio.model.Sesso;
import autonoleggio.validazioni.Validation;

public class ManagerDAOimpl implements ManagerDAO{

	// per semplificare
		private Autonoleggio autonoleggio = Autonoleggio.getInstance();
		
	
	
	@Override
	public List<Manager> list() throws Exception {
		List<Manager> managers = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(Dbms.filenameManager))) {
			String line;
			while ((line = br.readLine()) != null) {
				// devo controllare lunghezza array
				// correttamente altrimenti eccezione

				String[] parts = line.split(",");

				Manager manager = new Manager();

				if (parts.length == 11) {
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

						// popolo obj
						manager.setNome(nome);
						manager.setCognome(cognome);
						manager.setEmail(email);
						manager.setCodiceFiscale(codiceFiscale);
						manager.setSesso(sesso);
						manager.setLuogoDiNascita(luogoDiNascita);
						manager.setProvincia(provincia);
						manager.setDataDiNascita(dataDiNascita);
						manager.setRuolo(ruolo);
						manager.setUserName(userName);
						manager.setPassword(password);
					} catch (MyValidationEx | NumberFormatException e) {
						System.out.println(e.getMessage());
						throw new Exception("errore nell'acquisizione di uno degli elementi di managers.txt");
					}

				} else {
					throw new Exception("errore nel numero degli elementi di managers.txt");
				}
				
					managers.add(manager);
				
				
			}
		} catch (IOException e) {
			System.out.println("il file managers.txt non esiste");
		}
		autonoleggio.setManagers(managers);
		return managers;
	}

	@Override
	public Manager get(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Manager o) throws Exception {
		// attenzione a controllo null ed eccezioni
		if (o == null) {
			throw new Exception("null car?");
		}
		for (Manager manager : autonoleggio.getManagers()) {
			if (manager.equals(o)) {
				throw new Exception("manager già presente!");
			}
		}

		// backend
		autonoleggio.addManager(o);
		// ripristino db - attenzione a date null
		try (FileWriter writer = new FileWriter(Dbms.filenameManager)) {
			for (Manager manager : autonoleggio.getManagers()) {
				writer.write(manager.getNome() + "," + manager.getCognome() + "," + manager.getEmail() + ","
						+ manager.getCodiceFiscale() + "," + manager.getSesso() + "," + manager.getLuogoDiNascita()
						+ "," + manager.getProvincia() + "," + manager.getDataDiNascita() + "," + manager.getRuolo()
						+ "," + manager.getUserName() + "," + manager.getPassword() + "\n");
			}
		} catch (IOException e) {
			System.out.println("qualcosa andato storto nella insert di manager");
		}
	}

	@Override
	public void delete(Manager o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Manager o) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
