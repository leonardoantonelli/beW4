package TitoloDiVIaggio;

import javax.persistence.*;

@Entity
@Table(name= "biglietti")
@DiscriminatorValue("Biglietto")
public class Biglietto extends TitoloDiVIaggio {

	private Boolean timbratura;
	
	//Costruttori
	
	public Biglietto() {}
	
	public Biglietto(Boolean timbratura) {
		super();
		this.timbratura = timbratura ;
	}

	//Getter and Setter
	
	public void setTimbratura(Boolean timbratura) {
		this.timbratura = timbratura;
	}
	
	public Boolean getTimbratura() {
		return timbratura;
	}
	
}
