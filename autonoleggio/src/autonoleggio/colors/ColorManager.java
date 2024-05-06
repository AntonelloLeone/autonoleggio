package autonoleggio.colors;

public class ColorManager {

	public static final int NUMERO_COLORI = 4;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_TURQUOISE = "\u001B[36m";

	public static final String[] colori = {ANSI_GREEN,ANSI_RED,ANSI_YELLOW,ANSI_TURQUOISE};
	
	public static void elencoColori() {
		System.out.println("1 - " + ANSI_GREEN + "Verde" + ANSI_RESET);
		System.out.println("2 - " + ANSI_RED + "Rosso" + ANSI_RESET);
		System.out.println("3 - " + ANSI_YELLOW + "Giallo" + ANSI_RESET);
		System.out.println("4 - " + ANSI_TURQUOISE + "Turchese" + ANSI_RESET);

	}
}
