package TitoloDiVIaggio;

import java.util.InputMismatchException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import Enum.Periodicità;
import Main.GestioneTrasporti;

@Entity
@Table(name = "abbonamenti")
@DiscriminatorValue("Abbonamento")
public class Abbonamento extends TitoloDiVIaggio {

	@Enumerated(EnumType.STRING)
	private Periodicità periodicità;
	private Boolean validità;

	//Costruttori
	
	public Abbonamento () {}
	
	public Abbonamento (Boolean validità) {
		super();
		this.validità = validità;
		this.setPeriodicità();
	}
	
	public Abbonamento(Periodicità periodicità, Boolean validità) {
		super();
		this.periodicità = periodicità;
		this.validità = validità;
	}

	//Getter and Setter
	
	public Periodicità getPeriodicità() {
		return periodicità;
	}

	public void setPeriodicità() {
		int uscita = 0;
		while(uscita <= 0 || uscita >= 4) {
			System.out.println(">> Seleziona la durata dell'abbonamento 1 = Settimanale 2 = Mensile");
			try {
				  uscita = GestioneTrasporti.s.nextInt();
				  GestioneTrasporti.s.nextLine();
				switch(uscita){
				case 1:
					this.periodicità = Periodicità.SETTIMANALE;
					break;
				case 2:
					this.periodicità = Periodicità.MENSILE;
					break;
				}
			}catch(InputMismatchException e) {
				System.out.println("Utilizza un valore valido");
				GestioneTrasporti.s.nextInt();
			}
			
		}
	}

	public Boolean getValidità() {
		return validità;
	}
	
	
}
