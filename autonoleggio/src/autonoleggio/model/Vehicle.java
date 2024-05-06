package autonoleggio.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Vehicle {

	// campi base----------------------------------------
	private String type;
	private String brand;
	private int HourCost;
	private String targa; // PK
	private Status status;
	private LocalDateTime dateTimeTaken;

	// constructor
	
	
	public Vehicle(String type, String brand, int hourCost, String targa) {
		super();
		this.type = type;
		this.brand = brand;
		HourCost = hourCost;
		this.targa = targa;
		this.status = Status.DISPONIBILE;
		this.dateTimeTaken = null;
	}
	
	public Vehicle() {
		super();
	}


	
	// relathionship--------------------------------------
	// private Cliente user; // non serve lo segno in cliente

	// print
	

	




	// varie----------------------------------------------
	

	@Override
	public int hashCode() {
		return Objects.hash(targa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(targa, other.targa);
	}

	@Override
	public String toString() {
		return "Vehicle:" + type+", "+ brand +", "+ HourCost +", "+ targa
				+", "+ status  +", "+ dateTimeTaken;
	}

	// getter setter
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getHourCost() {
		return HourCost;
	}

	public void setHourCost(int hourCost) {
		HourCost = hourCost;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getDateTimeTaken() {
		return dateTimeTaken;
	}

	public void setDateTimeTaken(LocalDateTime dateTimeTaken) {
		this.dateTimeTaken = dateTimeTaken;
	}

	// gestione conto
	public double dammiSaldo() {
        // Calcola il tempo corrente
        LocalDateTime now = LocalDateTime.now();

        // dateTimeTaken nel futuro rispetto a "now" per evitare valori negativi!
        if (!dateTimeTaken.isBefore(now)) {
            throw new IllegalArgumentException("Error!");
        }

        // Calcola la differenza esatta in ore (double)
        double exactHours = -Duration.between(now, dateTimeTaken).toMinutes() / 60.0;
        
        // Arrotonda le ore al numero int
        long roundedHours = Math.round(exactHours);

        // Calcola il costo tot
        double cost = roundedHours * HourCost;

        // Restituisci il costo tot
        return cost;
    }

}
