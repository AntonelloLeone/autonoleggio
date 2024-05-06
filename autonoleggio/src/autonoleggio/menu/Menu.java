package autonoleggio.menu;

public class Menu {

	private String menu;
	private int numeroScelte;

	// builder and give menu
	public Menu(String... commandString) {
		super();
		this.menu = createMenu(commandString);
		this.numeroScelte=menu.split(",").length;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	

	public int getNumeroScelte() {
		return numeroScelte;
	}

	public void setNumeroScelte(int numeroScelte) {
		this.numeroScelte = numeroScelte;
	}

	private String createMenu(String... commandString) {
		int index = 1;
		String stringmenu = "";
		for (String stringItem : commandString) {
			stringmenu += index+" - "+ stringItem + "\n";
			index++;
		}
		return stringmenu;
	}
}
