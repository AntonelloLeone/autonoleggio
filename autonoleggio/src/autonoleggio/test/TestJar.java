package autonoleggio.test;

import java.io.File;

import autonoleggio.dbms.Dbms;

public class TestJar {

	public static String convertWindowsToUnix(String windowsPath) {
		// Sostituisci tutti i backslash con le barre oblique
		return windowsPath.replace("/", "\\\\");
	}

	public static void main(String[] args) {

		String windowsPath = Dbms.getPath();
		System.out.println(windowsPath);
		
		String unixPath = convertWindowsToUnix(windowsPath);
		System.out.println("Percorso Windows: " + windowsPath);
		System.out.println("Percorso Unix: " + unixPath);
		
		String a = unixPath.substring(2).replace("/", "\\").replace("bin/", "")+"src\\\\autonoleggio\\\\";
		System.out.println(a);

		System.out.println("C:\\Users\\Studente4.15\\eclipse-workspace\\autonoleggio\\src\\autonoleggio\\");
		System.out.println(windowsPath.replace("bin/", "").replace("/", "\\")+"src\\autonoleggio\\");
	}
//	static String percorsoBase = windowsPath.substring(1).replace("/", "\\").replace("bin\\", "")+"src\\autonoleggio\\";

}
