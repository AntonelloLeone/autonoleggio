package autonoleggio.middleware;

import java.time.LocalDate;
import java.util.List;

import autonoleggio.colors.ColorManager;
import autonoleggio.consolemanagement.ConsoleManagement;
import autonoleggio.dbms.Dbms;
import autonoleggio.encryption.Crittografia;
import autonoleggio.megafactory.BaseManager;
import autonoleggio.model.Automobile;
import autonoleggio.model.Batmobile;
import autonoleggio.model.Cliente;
import autonoleggio.model.Manager;
import autonoleggio.model.Role;
import autonoleggio.model.Status;
import autonoleggio.service.MyServiceFactory;
import autonoleggio.service.automobile.AutomobileService;
import autonoleggio.service.batmobile.BatmobileService;
import autonoleggio.service.cliente.ClienteService;
import autonoleggio.service.manager.ManagerService;

public class MiddleWare {

	// POTREI USARE UN MANAGER SINGLETON PER LA GESTIONE.........
	BaseManager baseManager = new BaseManager();
	
	private ConsoleManagement console = new ConsoleManagement();
	ClienteService clienteService = MyServiceFactory.getClienteServiceInstance();
	AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();
	ManagerService managerService = MyServiceFactory.getManagerServiceInstance();
	BatmobileService batmobileService = MyServiceFactory.getBatmobileServiceInstance();
	// -------------------------------------------------------------
	public void start() {
		
	
		startFlow();
	}

	// -------------------------------------------------------------
	public void startFlow() {
		System.out.println(Dbms.filenameAutomobili);
		// retrieve auto
		automobileService.lista();
		batmobileService.lista();
		// retrieve manager/batman
		managerService.lista();
		
		// retrieve clienti
		clienteService.lista();
		
		
		
		
		
		introMenuManagement(baseManager);
	}

	public void introMenuManagement(BaseManager baseManager) {
		while (true) {
			System.out.println("");
			System.out.println(baseManager.getIntro());
			System.out.println(baseManager.getMenuStart());
			// manage scanner [Test : yes ]
			int choice = 0;
			int check = console.giveInt("select choice:", 3, "riprova", "", "nein");
			if (console.getRet() == 1) {
				choice = check;
			}

			// gestione scelte
			switch (choice) {
			case 1:
				// mi deve chiedere accesso manager -> menu manager/batman
				String userNameM = console.giveUserName("Digita username del manager [8-12char]:", 3, "riprova", "", "nein");
				String passwordM = console.givePassword("Digita password del manager [8char+1special(!@#$%)]:", 3, "riprova", "", "nein");
				Manager attivo = new Manager();
				
				for (Manager manager : baseManager.getAutonoleggio().getManagers()) {
					if (userNameM.equals(manager.getUserName())
							&& passwordM.equals(Crittografia.decriptaPassword(manager.getPassword()))) {
						attivo = manager;
						System.out.println(attivo);

					}
				}
				if(attivo.getRuolo().toString().equals("BATMAN")) {
					menuBatman();
				}else {
					menuManager();
				}
				//menuManager();
				introMenuManagement(baseManager);
				break;
			case 2:
				String userNameCliente = console.giveUserName("Digita username del cliente [8-12char]:", 3, "riprova", "", "nein");
				String passwordCliente = console.givePassword("Digita password del cliente [8char+1special(!@#$%)]:", 3, "riprova", "", "nein");
				Cliente clienteAttivo = new Cliente();
				for (Cliente client : baseManager.getAutonoleggio().getClienti()) {
					if (userNameCliente.equals(client.getUserName())
							&& passwordCliente.equals(Crittografia.decriptaPassword(client.getPassword()))) {
						clienteAttivo = client;
						baseManager.getAutonoleggio().setClienteActive(clienteAttivo);

					}
				}
				if(clienteAttivo.getNome()!="null") {
					menuCliente();
				}else {
					introMenuManagement(baseManager);
				}
				break;
			case 3:
				System.out.println("Goobye!!!");
				System.exit(0);
				break;
			}
		}
	}

	public void menuManager() {
		while (true) {
			System.out.println("");
			System.out.println(baseManager.getMenuManager_1());

			int choice = 0;
			int check = console.giveInt("select choice:", 3, "riprova", "", "nein");
			if (console.getRet() == 1) {
				choice = check;
			}

			// gestione scelte
			switch (choice) {
			case 1:
				// view garage
				List<Automobile> automobili = automobileService.lista();
				if (automobili != null) {
					for (Automobile automobile : automobili) {
						System.out.println(automobile);
					}
				}
				break;
			case 2:
				// add auto
				Automobile automobile=null;
				// interview
				String type=console.giveString("Digita modello automobile:", 3, "riprova", "", "nein");
				
				String brand=console.giveString("Digita brand automobile:", 3, "riprova", "", "nein");
				
				int HourCost=console.giveInt("Digita un prezzo orario intero:", 3, "riprova", "", "nein");
				if(console.getRet()==0) {
					return; 
				}
				
				String targa=console.giveTarga("Digita una targa, ex: AC012IT:", 3, "riprova", "", "nein");
				
				// provo ad instanziare
				if(type!=null || brand!=null || HourCost!=0 || targa!=null) {
					automobile = new Automobile(type, brand, HourCost, targa);
				}
				 
				 
				 
				// poi tenta di inserirla
				try {
					automobileService.inserisciAutomobile(automobile);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				
				
				break;
			case 3:
				// delete auto
				int countDel = 0;
				for (Automobile automobileDel : baseManager.getAutonoleggio().getAutomobili()) {
					System.out.println(countDel + " - " + automobileDel.toString());
					countDel++;
				}

				if (baseManager.getAutonoleggio().getAutomobili().size() != 0) {
					int indexDel = console.giveInt("seleziona il numero dell'auto da eliminare:", 2, "rirpova", "ok",
							"err # delete");
					if (indexDel >= 0 && indexDel < baseManager.getAutonoleggio().getAutomobili().size()) {
						Automobile automobileToDelete = baseManager.getAutonoleggio().getAutomobili().get(indexDel);

						if (!automobileToDelete.getStatus().equals(Status.OCCUPATA)) {
							automobileService.eliminaAutomoble(automobileToDelete);
							System.out.println(automobileToDelete+" eliminata");
						} else {
							System.out.println("Impossibile eliminare un'auto NOLEGGIATA");

						}

					} else {
						System.out.println("Indice non valido");
					}

				} else {
					System.out.println("non ci sono proprio auto");
					menuManager();
				}

				break;
			case 4:
				// insert cliente
				Cliente cliente = null;
				// ask
				String nome = console.giveString("Digita nome del cliente:", 3, "riprova", "", "nein");
				String cognome = console.giveString("Digita cognome del cliente:", 3, "riprova", "", "nein");
				String mail = console.giveMail("Digita mail del cliente:", 3, "riprova", "", "nein");
				String sexTest = console.giveSesso("Digita sesso del cliente [maschio,femmina]:", 3, "riprova", "", "nein").toString();
				autonoleggio.model.Sesso sesso = (sexTest.equals(autonoleggio.model.Sesso.MASCHIO.toString())?autonoleggio.model.Sesso.MASCHIO:autonoleggio.model.Sesso.FEMMINA);
				String luogoDiNascita = console.giveString("Digita luogo di nascita del cliente:", 3, "riprova", "", "nein");
				String provincia = console.giveString("Digita provincia del cliente:", 3, "riprova", "", "nein");
				LocalDate dataDiNascita = console.giveData("Digita data di nascita del cliente [dd/mm/yyyy]:", 3, "riprova", "", "nein");
				String patente = console.givePatente("Digita patente (4Up char,2numeri):", 3, "riprova", "", "nein");
				String userName = console.giveUserName("Digita username del cliente [8-12char]:", 3, "riprova", "", "nein");
				String password = console.givePassword("Digita password del cliente [8char+1special(!@#$%)]:", 3, "riprova", "", "nein");
				// provo ad instanziare
				if(true) {
					cliente = new Cliente(nome, cognome, mail, sesso, luogoDiNascita, provincia, dataDiNascita, Role.USER, userName, Crittografia.criptaPassword(password), patente,null);
				}
				
				// try to insert
				try {
					clienteService.inserisciCliente(cliente);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				// Search Menù
				menuSearchForManager();
				break;
			case 6:
				// Color Settings
				menuColors();
				break;
			case 7:
				// view managers
				List<Manager> managers = managerService.lista();
				if (managers != null) {
					for (Manager manager : managers) {
						if(!manager.getRuolo().toString().equals("BATMAN")) {
							System.out.println(manager.esponiView());
						}
						
					}
				}
				break;
			case 8:
				// add manager
				Manager manager = null;
				// ask
				String nomeM = console.giveString("Digita nome del manager:", 3, "riprova", "", "nein");
				String cognomeM = console.giveString("Digita cognome del manager:", 3, "riprova", "", "nein");
				String mailM = console.giveMail("Digita mail del manager:", 3, "riprova", "", "nein");
				String sexTestM = console.giveSesso("Digita sesso del manager [maschio,femmina]:", 3, "riprova", "", "nein").toString();
				autonoleggio.model.Sesso sessoM = (sexTestM.equals(autonoleggio.model.Sesso.MASCHIO.toString())?autonoleggio.model.Sesso.MASCHIO:autonoleggio.model.Sesso.FEMMINA);
				String luogoDiNascitaM = console.giveString("Digita luogo di nascita del manager:", 3, "riprova", "", "nein");
				String provinciaM = console.giveString("Digita provincia del manager:", 3, "riprova", "", "nein");
				LocalDate dataDiNascitaM = console.giveData("Digita data di nascita del manager [dd/mm/yyyy]:", 3, "riprova", "", "nein");
				String userNameM = console.giveUserName("Digita username del manager [8-12char]:", 3, "riprova", "", "nein");
				String passwordM = console.givePassword("Digita password del manager [8char+1special(!@#$%)]:", 3, "riprova", "", "nein");
				// provo ad instanziare
				if(true) {
					manager = new Manager(nomeM, cognomeM, mailM, sessoM, luogoDiNascitaM, provinciaM, dataDiNascitaM , Role.MANAGER, userNameM, Crittografia.criptaPassword(passwordM));
				}
				
				// try to insert
				try {
					managerService.inserisciManager(manager);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 9:
				introMenuManagement(baseManager);
				break;
			case 10:
				System.out.println("Goobye!!!");
				System.exit(0);
				break;
			case 99:
				menuBatman();
				break;
			}
		}

	}

	public void menuColors() {
		System.out.println(baseManager.getMenuColoursString());
		int choice = console.giveInt("seleziona messagio a cui cambiare colore o esci:", 1,
				"riprova a selezionare;", "ok", "out of range");
//		System.out.println(console.getRet()[0]);
		int choiceNumber = baseManager.getMenuColours().getNumeroScelte();
		if (choice > 0 && choice <= choiceNumber && console.getRet() == 1) {
			if (choiceNumber == 1) {
				ColorManager.elencoColori();
				int number = console.giveInt("seleziona colore:", 1, "riprova a selezionare;", "ok",
						"colore non modificato");
				if (number > 0 && number <= ColorManager.NUMERO_COLORI && console.getRet() == 1) {
					console.setOkMsgColour(ColorManager.colori[number - 1]);
				}
			}
			if (choiceNumber == 2) {
				ColorManager.elencoColori();
				int number = console.giveInt("seleziona colore:", 1, "riprova a selezionare;", "ok",
						"colore non modificato");
				if (number > 0 && number <= ColorManager.NUMERO_COLORI && console.getRet() == 1) {
					console.setRetryMsgColour(ColorManager.colori[number - 1]);
				}
			}
			if (choiceNumber == 3) {
				ColorManager.elencoColori();
				int number = console.giveInt("seleziona colore:", 1, "riprova a selezionare;", "ok",
						"colore non modificato");
				if (number > 0 && number <= ColorManager.NUMERO_COLORI && console.getRet() == 1) {
					console.setErrMsgColour(ColorManager.colori[number - 1]);
				}
			}

			if (choiceNumber == 4) {
				return;
			}

		}
	}

	public void menuSearchForManager() {
		while (true) {
			System.out.println("");
			System.out.println(baseManager.getSearch());
			
			// manage scanner [Test : yes ]
			int choice = 0;
			int check = console.giveInt("select choice:", 3, "riprova", "", "nein");
			if (console.getRet() == 1) {
				choice = check;
			}

			// gestione scelte
			switch (choice) {
			case 1:
				// ViewClienti
				List<Cliente> clienti = clienteService.lista();
				if (clienti != null) {
					for (Cliente cliente : clienti) {
						System.out.println(cliente.esponiView());
					}
				}
				break;
			case 2:
				//ViewNoleggiabili
				
				break;
			case 3:
				//ViewClientiAttivi
				
				break;
			case 4:
				//back
				menuManager();
				break;
			case 5:
				System.out.println("Goobye!!!");
				System.exit(0);
				break;
			}
		}
	}
	
	public void menuCliente() {
		System.out.println("");
		System.out.println(baseManager.getMenuCliente());
		
		// manage scanner [Test : yes ]
		int choice = 0;
		int check = console.giveInt("select choice:", 3, "riprova", "", "nein");
		if (console.getRet() == 1) {
			choice = check;
		}

		// gestione scelte
		switch (choice) {
		case 1:
			
			//noleggia
			if(baseManager.getAutonoleggio().getClienteActive().getPatente().equals("null")) {
				System.out.println("questo cliente non ha la patente");
				menuCliente();
			}
			
			
			int countNolo = 0;
			for (Automobile automobileNolo : baseManager.getAutonoleggio().getAutomobili()) {
				System.out.println(countNolo + " - " + automobileNolo.toString());
				countNolo++;
			}

			if (baseManager.getAutonoleggio().getAutomobili().size() != 0) {
				int indexNolo = console.giveInt("seleziona il numero dell'auto da noleggiare:", 2, "riprova", "ok",
						"err # delete");
				if (indexNolo >= 0 && indexNolo < baseManager.getAutonoleggio().getAutomobili().size()) {
					Automobile automobileToNolo = baseManager.getAutonoleggio().getAutomobili().get(indexNolo);

					if (!automobileToNolo.getStatus().equals(Status.OCCUPATA)) {
						automobileService.noleggiaAutomobile(automobileToNolo);
//						automobileService.lista();
						
						baseManager.getAutonoleggio().getClienteActive().setAutomobile(automobileToNolo);
						baseManager.getAutonoleggio().getClienteActive().setTarga(automobileToNolo.getTarga());
						clienteService.noleggiaAuto(baseManager.getAutonoleggio().getClienteActive());
						System.out.println(automobileToNolo+" noleggiata");
						menuCliente();
					} else {
						System.out.println("Impossibile noleggiare l'auto");
						menuCliente();

					}

				} else {
					System.out.println("Indice non valido");
					menuCliente();
				}

			} else {
				System.out.println("non ci sono proprio auto");
				menuCliente();
			}

			break;
		case 2:
			// consegna auto
				automobileService.consegnaAuto(baseManager.getAutonoleggio().getClienteActive().getAutomobile());
				baseManager.getAutonoleggio().getClienteActive().setAutomobile(null);
				baseManager.getAutonoleggio().getClienteActive().setTarga(null);
				clienteService.consegnaAuto(baseManager.getAutonoleggio().getClienteActive());
				menuCliente();
			

			break;
		case 3:
			//find price
			for (Automobile autoNameItem : baseManager.getAutonoleggio().getAutomobili()) {
				System.out.println(autoNameItem.getHourCost());
			}
			int priceInput=console.giveInt("digita il prezzo orario da lista sopra", 2, "riprova", "ok",
						"err typeInput");
			
			if(console.getRet()==1) {
				List<Automobile> listaPerPrezzo = automobileService.trovaByPrice(priceInput);
				for (Automobile automobilePriceItem : listaPerPrezzo) {
					System.out.println(automobilePriceItem.toString());
				}
			}
			menuCliente();
			break;
		case 4:
			//find name
			for (Automobile autoNameItem : baseManager.getAutonoleggio().getAutomobili()) {
				System.out.println(autoNameItem.getType());
			}
			String typeInput=console.giveString("digita selezione tipo auto da lista sopra", 2, "riprova", "ok",
						"err typeInput");
			
			if(console.getRet()==1) {
				List<Automobile> listaPerNome = automobileService.trovaByName(typeInput);
				for (Automobile automobileNameItem : listaPerNome) {
					System.out.println(automobileNameItem.toString());
				}
			}
			menuCliente();
			break;
		case 5:// back
			menuManager();
			break;
		case 6:
			System.out.println("Goobye!!!");
			System.exit(0);
			break;

		}
	}
	
	public void menuBatman() {
		System.out.println("");
		System.out.println("""
				..........................................................................................
				........................................,........,........................................
				...........................,:;+,........?%,....,%?........,+;:,...........................
				.......................,;*%#@@;.........?@S****S@?.........;@@#%*;,.......................
				....................,+%#@@@@@+..........?@@@@@@@@?..........+@@@@@#%+,....................
				..................;%@@@@@@@@@,..........#@@@@@@@@#..........,@@@@@@@@@%;..................
				................:%@@@@@@@@@@@?,.......,?@@@@@@@@@@?,.......,?@@@@@@@@@@@%:................
				...............+@@@@@@@@@@@@@@@%?***?%#@@@@@@@@@@@@#%?***?%@@@@@@@@@@@@@@@+...............
				..............;@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@;..............
				..............S@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@S..............
				..............#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#..............
				..............?@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@?..............
				..............,S@@@@@@@@@@#SS#@@@@@#SS#@@@@@@@@@@@@#SS#@@@@@#SS#@@@@@@@@@@S,..............
				...............,?@@@@@@@?:,..,:+S@+,..,:+S@@@@@@S+:,..,+@S+:,..,:?@@@@@@@?,...............
				.................;%@@@@%........,:........+#@@#+........:,........%@@@@%;.................
				...................:?#@S,..................:##:..................,#@#?:...................
				.....................,;*+...................;;...................+*;,.....................
				..........................................................................................
				""");
		System.out.println(baseManager.getMenuBatman());

		// manage scanner [Test : yes ]
		int choice = 0;
		int check = console.giveInt("select choice:", 3, "riprova", "", "nein");
		if (console.getRet() == 1) {
			choice = check;
		}

		// gestione scelte
		switch (choice) {
		case 1:

			// view all batmobili
			List<Batmobile> batmobili = batmobileService.lista();
			if (batmobili != null) {
				for (Batmobile batmobile : batmobili) {
					System.out.println(batmobile);
				}
			}
			menuBatman();
			break;
		case 2:
			// add batmobili
			Batmobile batmobile=null;
			// interview
			String type=console.giveString("Digita modello batmobile:", 3, "riprova", "", "nein");
			
			String brand=console.giveString("Digita brand batmobile:", 3, "riprova", "", "nein");
			
			int HourCost=0;
			
			String targa=console.giveTarga("Digita una targa, ex: AC012IT:", 3, "riprova", "", "nein");
			
			// provo ad instanziare
			if(type!=null || brand!=null || HourCost!=0 || targa!=null) {
				batmobile = new Batmobile(type, brand, HourCost, targa);
			}
			 
			 
			 
			// poi tenta di inserirla
			try {
				batmobileService.inserisciBatmobile(batmobile);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			menuBatman();
			break;
		case 3:
			// back
			menuManager();
			break;

		case 4:
			System.out.println("Goobye!!!");
			System.exit(0);
			break;

		}
	}

}

