package autonoleggio.megafactory;

import autonoleggio.menu.Menu;
import autonoleggio.model.Autonoleggio;

public class BaseManager {
	    
		
	// devo generare i menu
	// deve avere una console - non è detto
	
	private Autonoleggio autonoleggio;
	
	


	public BaseManager() {
		super();
		this.autonoleggio = Autonoleggio.getInstance(); // unica istanza di Autonoleggio
		
	}
	
	
	
	
	
	private String intro = """
###########################################################################################################			
            
   ##                ##                                  ##                                                 
  ####    ##   ##  ######    #####   ## ###    #####     ##      #####    ######   ######  ####      #####  
  ## #    ##   ##    ##     ##   ##  ###  ##  ##   ##    ##     ##   ##  ##   ##  ##   ##    ##     ##   ## 
 ######   ##   ##    ##     ##   ##  ##   ##  ##   ##    ##     #######  ##   ##  ##   ##    ##     ##   ## 
 ##   #   ##  ###    ##     ##   ##  ##   ##  ##   ##    ##     ##        ######   ######    ##     ##   ## 
###   ##   ### ##     ###    #####   ##   ##   #####    ####     #####        ##       ##  ######    #####  
                                                                          #####    #####                    
###########################################################################################################			
			""";
	
	private String menuStart = new Menu(
			"Login Manager",
			"Login Client",
			"Quit"
			).getMenu();
	
	private String menuManager_1 = new Menu(
			"View Garage",
			"Add auto",
			"Delete auto",
			"Add Cliente",
			"Search Menù",
			"Color Settings",
			"View managers",
			"Add manager",
			"Back",
			"Quit"
			).getMenu();
	
	private Menu menuColours = new Menu(
			"okMsgColour",
			"retryMsgColour",
			"errMsgColour",
			"back");
	
	private String menuColoursString=menuColours.getMenu();
	
	
	
	private String Search = new Menu(
			"View Clienti",
			"Noleggia Automobile",
			"Consegna Automobile",
			"Back",
			"Quit"
			
			).getMenu();
	
	private String menuCliente = new Menu(
			"Noleggia automobile",
			"Consegna Automobile",
			"Cerca automobile per prezzo",
			"Cerca automobile per nome",
			"Back",
			"Quit"
			).getMenu();
	
	
	private String menuBatman = new Menu(
			"View Batmobili",
			"Add Batmobile",
			"Back",
			"Quit"
			).getMenu();
	
	// GETTER SETTER


	public String getSearch() {
		return Search;
	}

	public String getMenuBatman() {
		return menuBatman;
	}

	public String getMenuCliente() {
		return menuCliente;
	}

	public String getMenuStart() {
		return menuStart;
	}

	public String getIntro() {
		return intro;
	}

	public String getMenuManager_1() {
		return menuManager_1;
	}

	public String getMenuColoursString() {
		return menuColoursString;
	}

	public Menu getMenuColours() {
		return menuColours;
	}

	public Autonoleggio getAutonoleggio() {
		return autonoleggio;
	}



	
	

	
	
	

	

}
