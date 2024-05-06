package autonoleggio.model;

import java.time.LocalDate;

public class Cliente extends Persona {
	
	
	
	public Cliente() {
		super();

	}
	
	

	public Cliente(String nome, String cognome, String email, Sesso sesso, String luogoDiNascita, String provincia,
			LocalDate dataDiNascita, Role ruolo, String userName, String password, String patente,Automobile auto) {
		super(nome, cognome, email, sesso, luogoDiNascita, provincia, dataDiNascita, ruolo, userName, password);
		this.patente = patente;
	}



	// campi base (se ha la patente non serve età ma possiamo controllare)
	protected String patente;
	

	// relathionship
	Automobile automobile;
	protected String targa;
	@Override
	public String esponiView() {
		return super.esponiView()+", patente=" +patente+", targa=" +targa;
		
	}

	
	// getter setter
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Automobile getAutomobile() {
		return automobile;
	}

	public void setAutomobile(Automobile automobile) {
		this.automobile = automobile;
	}



	public String getTarga() {
		return targa;
	}



	public void setTarga(String targa) {
		this.targa = targa;
	}



}
