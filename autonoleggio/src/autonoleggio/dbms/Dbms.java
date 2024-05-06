package autonoleggio.dbms;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import autonoleggio.model.Autonoleggio;
import autonoleggio.test.Main;

public class Dbms {
	
	private static String basePAth;
	public  static String filenameAutomobili = ""; 
	public  static String filenameClienti = ""; 
	public  static String filenameManager = ""; 
	public  static String filenameBatmobili =""; 
	
	static {
		// auto load of the real path - attenzione a renderla static, static block to
		// set

//	private static String basePAth = "C:\\Users\\Studente4.15\\eclipse-workspace\\autonoleggio\\src\\autonoleggio\\";
		basePAth = getPath().replace("bin/", "").replace("/", "\\")+"src\\autonoleggio\\";
		filenameAutomobili += basePAth + "automobili.txt"; 
		filenameClienti += basePAth + "clienti.txt";
		filenameManager += basePAth + "manager.txt";
		filenameBatmobili += basePAth + "batmobili.txt";

	}
	
	public static String convertWindowsToUnix(String windowsPath) {
		// Sostituisci tutti i backslash con le barre oblique
		return windowsPath.replace("/", "\\");
	}

	

	public static String getPath() {
		ProtectionDomain protectionDomain = Main.class.getProtectionDomain();
		CodeSource codeSource = protectionDomain.getCodeSource();

		if (codeSource != null) {
			String jarPath = codeSource.getLocation().getPath();
			// Rimuovi il prefisso "file:/" e decodifica l'URL
			try {
				String decodedPath = URLDecoder.decode(jarPath, "UTF-8");
				return decodedPath.substring(1); // Rimuovi il primo carattere che è "/"
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Impossibile recuperare il percorso del JAR.");
		}
		return "";
	}


	// Singleton
	private static Dbms instance = null;
	
	private Dbms() {
		super();
	}

	public static Dbms getInstance() {
		if (instance == null) {
			instance = new Dbms();
		}
		return instance;
	}
	
	// utility
	// -------------------------------------------------------------------------

	
	// Mapping iniziale se c'è il file (   ) altrimenti crea a azzera tutto, evito disambiguazioni per ora
	// poi va implementato un modo per tutelarlo - tipo BACK_UP
	public void dbRetrieve(Autonoleggio autonoleggio) {
		
	}
}
