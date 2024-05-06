package autonoleggio.validazioni;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import autonoleggio.eccezioni.MyValidationEx;
import autonoleggio.model.Role;
import autonoleggio.model.Sesso;
import autonoleggio.model.Status;

public class Validation {

	private static int year_max = 1920;
	private static String intRegex = "[0-9]+";
	private static String sCfRegex = "^[A-Z]{3}[A-Z]{3}\\d{2}(M|F)[A-Z]{2}$";
	// Regex targa italiana (2 lettere, 3 numeri, 2lettere) ex: AC012IT
	private static String sTargaRegex = "^[A-Z]{2}\\d{3}[A-Z]{2}$";
	private static String sessoRegex = "^(MASCHIO|FEMMINA)$";
	private static String userNameRegex = "^[a-zA-Z0-9]{8,12}$";
//	private static String pwdRegex="^(?=.*[!@#$%^&*()])(?=(?:.*[a-zA-Z0-9]){7}[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8}$";
	private static String pwdRegex="^(?=.*[!@#$%^&*()-+]).{8}$";
	private static String sMailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private static String sPatenteRegex = "^[A-Z]{4}\\d{2}$";
	// automobili --------------------------------------------------------------------------
	public static LocalDateTime checkDateTime(String text) throws MyValidationEx {
		if (text.equals("null")) {
			return null;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		try {
			LocalDateTime dateTime = LocalDateTime.parse(text, formatter);
			return dateTime;
		} catch (DateTimeParseException e) {
			throw new MyValidationEx("La Datetime non è valida: " + text);
		}
	}

	public static Status checkStatus(String text) throws MyValidationEx {
		switch (text.toUpperCase()) {
		case "DISPONIBILE":
			return Status.DISPONIBILE;
		case "OCCUPATA":
			return Status.OCCUPATA;
		default:
			throw new MyValidationEx("Lo status non è valido: " + text);
		}
	}



	public static String checkTarga(String text) throws MyValidationEx {
		if (!text.matches(sTargaRegex)) {
			throw new MyValidationEx("La targa non è valida: " + text);
		}
		return text;
	}

	// clienti --------------------------------------------------------------------------
	
	public static String checkPatente(String text) throws MyValidationEx {
		if (!text.matches(sPatenteRegex)) {
			throw new MyValidationEx("La patente non è valido: " + text);
		}
		return text;
	}
	

	// manager --------------------------------------------------------------------------
	
	public static Role checkRuolo(String text) throws MyValidationEx {
		switch (text.toUpperCase()) {
		case "BATMAN":
			return Role.BATMAN;
		case "MANAGER":
			return Role.MANAGER;
		case "USER":
			return Role.USER;
		case "DBA":
			return Role.DBA;
		default:
			throw new MyValidationEx("Il ruolo non è valido: " + text);
		}
	}
	
	// persona

	public static String checkUserName(String text) throws MyValidationEx {
		if (!text.matches(userNameRegex)) {
			throw new MyValidationEx("Lo username non è valido: " + text);
		}
		return text;
	}
	
	
	public static String checkPassword(String text) throws MyValidationEx {
		if (!text.matches(pwdRegex)) {
			throw new MyValidationEx("La password non è valida: " + text);
		}
		return text;
	}
	
	public static String checkMail(String text) throws MyValidationEx {
		if (!text.matches(sMailRegex)) {
			throw new MyValidationEx("La mail non è valida: " + text);
		}
		return text;
	}
	
	public static String checkCF(String text) throws MyValidationEx {
		if (!text.matches(sCfRegex)) {
			throw new MyValidationEx("Il codice fiscale non è valido: " + text);
		}
		return text;
	}
	
	

	
	public static Sesso checkSesso(String text) throws MyValidationEx {
		switch (text.toUpperCase()) {
		case "MASCHIO":
			return Sesso.MASCHIO;
		case "FEMMINA":
			return Sesso.FEMMINA;
		default:
			throw new MyValidationEx("Il sesso non è valido: " + text);
		}
	}
	public static LocalDate checkDate(String text) throws MyValidationEx {
	    if (text.equals("null")) {
	        return null;
	    }

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    try {
	        LocalDate date = LocalDate.parse(text, formatter);
	        return date;
	    } catch (DateTimeParseException e) {
	        throw new MyValidationEx("La Data non è valida: " + text);
	    }
	}
}
