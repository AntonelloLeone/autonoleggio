package autonoleggio.model;

import java.time.LocalDate;

public class Manager extends Persona{

	public Manager(String nome, String cognome, String email, Sesso sesso, String luogoDiNascita, String provincia,
			LocalDate dataDiNascita, Role ruolo, String userName, String password) {
		super(nome, cognome, email, sesso, luogoDiNascita, provincia, dataDiNascita, ruolo, userName, password);
		// TODO Auto-generated constructor stub
	}
	
	
	public Manager() {
		
	}

}
