package autonoleggio.dao.batmobile;

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
import autonoleggio.model.Batmobile;
import autonoleggio.model.Status;
import autonoleggio.validazioni.Validation;

public class BatmobileDAOimpl implements BatmobileDAO{
	
	// per semplificare
		private Autonoleggio autonoleggio = Autonoleggio.getInstance();

		@Override
		public List<Batmobile> list() throws Exception {
			List<Batmobile> batmobili = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(Dbms.filenameBatmobili))) {
				String line;
				while ((line = br.readLine()) != null) {
					// devo controllare lunghezza array
					// correttamente altrimenti eccezione

					String[] parts = line.split(",");

					Batmobile batmobile = new Batmobile();

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
							batmobile.setType(type);
							batmobile.setBrand(brand);
							batmobile.setHourCost(HourCost);
							batmobile.setTarga(targa);
							batmobile.setStatus(status);
							batmobile.setDateTimeTaken(dateTimeTaken);
						} catch (MyValidationEx | NumberFormatException e) {
							System.out.println(e.getMessage());
							throw new Exception("errore nell'acquisizione di uno degli elementi di batmobili.txt");
						}

					} else {
						throw new Exception("errore nel numero degli elementi di batmobili.txt");
					}

					batmobili.add(batmobile);
				}
			} catch (IOException e) {
				System.out.println("il file batmobili.txt non esiste, ora c'è");
				try (FileWriter writer = new FileWriter(Dbms.filenameBatmobili)) {
				    // Vuoto -crea
				} catch (IOException eX) {
				    System.out.println("Qualcosa è andato storto nella creazione del file batmobili");
				}
			}
			autonoleggio.setBatmobili(batmobili);
			return batmobili;
		}

		@Override
		public Batmobile get(String text) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void insert(Batmobile o) throws Exception {
			// attenzione a controllo null ed eccezioni
			if(o==null) {
				throw new Exception("null car?");
			}
			for (Batmobile batmobile : autonoleggio.getBatmobili()) {
				if(batmobile.equals(o)) {
					throw new Exception("automobile già presente!");
				}
			}
			
			//backend
			autonoleggio.addBatmobile(o);
			//ripristino db - attenzione a date null
			
			try (FileWriter writer = new FileWriter(Dbms.filenameBatmobili)) {
				for (Batmobile batmobile : autonoleggio.getBatmobili()) {
	                writer.write(batmobile.getType()+","+batmobile.getBrand()+","+batmobile.getHourCost() +","+
	                		batmobile.getTarga()+","+batmobile.getStatus().toString()+","+batmobile.getDateTimeTaken()+"\n");
	            }
			} catch (IOException e) {
				System.out.println("qualcosa andato storto nella insert di batmobile");
			}
			
		}

		@Override
		public void delete(Batmobile o) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void update(Batmobile o) throws Exception {
			// TODO Auto-generated method stub
			
		}

}
