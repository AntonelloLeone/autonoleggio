package autonoleggio.dao.automobile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import autonoleggio.dbms.Dbms;
import autonoleggio.eccezioni.MyValidationEx;
import autonoleggio.model.Automobile;
import autonoleggio.model.Autonoleggio;
import autonoleggio.model.Cliente;
import autonoleggio.model.Status;
import autonoleggio.validazioni.Validation;

public class AutomobileDAOimpl implements AutomobileDAO {

	// per semplificare
	private Autonoleggio autonoleggio = Autonoleggio.getInstance();
	

	@Override
	public List<Automobile> list() throws Exception {
		List<Automobile> automobili = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(Dbms.filenameAutomobili))) {
			String line;
			while ((line = br.readLine()) != null) {
				// devo controllare lunghezza array
				// correttamente altrimenti eccezione

				String[] parts = line.split(",");

				Automobile automobile = new Automobile();

				if (parts.length == 6) {
					// valido i campi
					try {
						String type = parts[0].trim();
						String brand = parts[1].trim();
						int HourCost=Integer.parseInt(parts[2].trim());
						String targa = Validation.checkTarga(parts[3].trim());
						Status status = Validation.checkStatus(parts[4].trim());
						LocalDateTime dateTimeTaken= Validation.checkDateTime(parts[5].trim());
						// popolo obj
						automobile.setType(type);
						automobile.setBrand(brand);
						automobile.setHourCost(HourCost);
						automobile.setTarga(targa);
						automobile.setStatus(status);
						automobile.setDateTimeTaken(dateTimeTaken);
					} catch (MyValidationEx | NumberFormatException e) {
						System.out.println(e.getMessage());
						throw new Exception("errore nell'acquisizione di uno degli elementi di automobili.txt");
					}

				} else {
					throw new Exception("errore nel numero degli elementi di automobili.txt");
				}

				automobili.add(automobile);
			}
		} catch (IOException e) {
			System.out.println("il file automobili.txt non esiste, ora c'è");
			try (FileWriter writer = new FileWriter(Dbms.filenameAutomobili)) {
			    // Vuoto -crea
			} catch (IOException eX) {
			    System.out.println("Qualcosa è andato storto nella creazione del file automobili");
			}
		}
		autonoleggio.setAutomobili(automobili);
		return automobili;
	}

	@Override
	public Automobile get(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Automobile o) throws Exception {
		// attenzione a controllo null ed eccezioni
		if(o==null) {
			throw new Exception("null car?");
		}
		for (Automobile automobile : autonoleggio.getAutomobili()) {
			if(automobile.equals(o)) {
				throw new Exception("automobile già presente!");
			}
		}
		
		//backend
		autonoleggio.addAutomobile(o);
		//ripristino db - attenzione a date null
		
		try (FileWriter writer = new FileWriter(Dbms.filenameAutomobili)) {
			for (Automobile automobile : autonoleggio.getAutomobili()) {
                writer.write(automobile.getType()+","+automobile.getBrand()+","+automobile.getHourCost() +","+
                		automobile.getTarga()+","+automobile.getStatus().toString()+","+automobile.getDateTimeTaken()+"\n");
            }
		} catch (IOException e) {
			System.out.println("qualcosa andato storto nella insert di automobile");
		}
	}

	@Override
	public void delete(Automobile o) {
		// elimina da list automobili
		autonoleggio.getAutomobili().remove(o);
		// riscrive il db
		try (FileWriter writer = new FileWriter(Dbms.filenameAutomobili)) {
			for (Automobile automobile : autonoleggio.getAutomobili()) {
				// Scrivi ogni automobile nel file
				writer.write(automobile.getType() + "," + automobile.getBrand() + "," + automobile.getHourCost() + ","
						+ automobile.getTarga() + "," + automobile.getStatus().toString() + "," + automobile.getDateTimeTaken()
						+ "\n");
			}
		} catch (IOException e) {
			System.out.println("Errore durante la scrittura nel file automobili");
		}
	}

	@Override
	public List<Cliente> viewFree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Automobile o) throws Exception {
		
		
	}

	@Override
	public void noleggia(Automobile o) {
		o.setStatus(Status.OCCUPATA);
		o.setDateTimeTaken(LocalDateTime.now());
		try (FileWriter writer = new FileWriter(Dbms.filenameAutomobili)) {
			for (Automobile automobile : autonoleggio.getAutomobili()) {
                writer.write(automobile.getType()+","+automobile.getBrand()+","+automobile.getHourCost() +","+
                		automobile.getTarga()+","+automobile.getStatus().toString()+","+automobile.getDateTimeTaken()+"\n");
            }
		} catch (IOException e) {
			System.out.println("qualcosa andato storto nella insert di automobile");
		}
	}

	@Override
	public void consegna(Automobile o) {
		o.setStatus(Status.DISPONIBILE);
		// calcolo prezzo lo stampo e svuoto
		System.out.println("Pagare Euro: "+o.dammiSaldo());
		o.setDateTimeTaken(null);
		
		try (FileWriter writer = new FileWriter(Dbms.filenameAutomobili)) {
			for (Automobile automobile : autonoleggio.getAutomobili()) {
                writer.write(automobile.getType()+","+automobile.getBrand()+","+automobile.getHourCost() +","+
                		automobile.getTarga()+","+automobile.getStatus().toString()+","+automobile.getDateTimeTaken()+"\n");
            }
		} catch (IOException e) {
			System.out.println("qualcosa andato storto nel noleggio - auto");
		}
	}

	@Override
	public List<Automobile> findByName(String s) {
		List<Automobile> automobili = new ArrayList<>();
		for (Automobile automobile : autonoleggio.getAutomobili()) {
			if(automobile.getType().equals(s)) {
				automobili.add(automobile);
			}
		}
		return automobili;
	}

	@Override
	public List<Automobile> findByPrice(int i) {
		List<Automobile> automobili = new ArrayList<>();
		for (Automobile automobile : autonoleggio.getAutomobili()) {
			if(automobile.getHourCost()<=i) {
				automobili.add(automobile);
			}
		}
		return automobili;
	}
}
