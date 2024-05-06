package autonoleggio.model;

import java.time.LocalDate;
import java.util.Objects;

public  class Persona {
	
	// campi base
	protected String nome;
	protected String cognome;
	protected String email;
	protected String codiceFiscale; // warning crea e give
	protected Sesso sesso;
	protected String luogoDiNascita;
	protected String provincia;
	protected LocalDate dataDiNascita;
	protected Role ruolo;
	
	// campi sicurezza
	protected String userName;
	protected String password;
	

	// relathionship
	
	// varie
	
	
	// Construction
	public Persona(String nome, String cognome, String email, Sesso sesso, String luogoDiNascita, String provincia,
			LocalDate dataDiNascita, Role ruolo, String userName, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.sesso = sesso;
		this.luogoDiNascita = luogoDiNascita;
		this.provincia = provincia;
		this.dataDiNascita = dataDiNascita;
		this.ruolo = ruolo;
		this.userName = userName;
		this.password = password;
		this.codiceFiscale = calcolaCodiceFiscale();
	}

	public Persona() {
		super();
	}

	// utility methods
	public int giveEta() {
		return LocalDate.now().getYear()-dataDiNascita.getYear();
	}
	


	protected String calcolaCodiceFiscale() {
		if (nome == null || cognome == null || dataDiNascita == null || sesso == null || provincia == null) {
	        return null;
	    }

	    // Costruisci il codice fiscale usando le informazioni disponibili
	    String codiceFiscale = nome.substring(0, Math.min(nome.length(), 3)).toUpperCase(); // Prime tre lettere del nome
	    codiceFiscale += cognome.substring(0, Math.min(cognome.length(), 3)).toUpperCase(); // Prime tre lettere del cognome
	    codiceFiscale += String.valueOf(dataDiNascita.getYear()).substring(2); // Le ultime due cifre dell'anno di nascita
	    codiceFiscale += (sesso == Sesso.MASCHIO) ? "M" : "F"; // Sesso
	    codiceFiscale += provincia.toUpperCase(); // Provincia

	    return codiceFiscale;
	}


	
	// control flow
	@Override
	public String toString() {
		return "Persona [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", codiceFiscale="
				+ codiceFiscale + ", sesso=" + sesso + ", luogoDiNascita=" + luogoDiNascita + ", provincia=" + provincia
				+ ", dataDiNascita=" + dataDiNascita + ", RUOLO=" + ruolo+", userName=" + userName + ", password=" + password + "]";
	}
	
	public String esponiView() {
		return "Persona [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", codiceFiscale="
				+ codiceFiscale + ", sesso=" + sesso + ", luogoDiNascita=" + luogoDiNascita + ", provincia=" + provincia
				+ ", dataDiNascita=" + dataDiNascita + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale);
	}


	
	
	// setter getter
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public String getLuogoDiNascita() {
		return luogoDiNascita;
	}

	public void setLuogoDiNascita(String luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Role getRuolo() {
		return ruolo;
	}

	public void setRuolo(Role ruolo) {
		this.ruolo = ruolo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
