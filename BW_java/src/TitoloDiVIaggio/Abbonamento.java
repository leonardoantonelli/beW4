package TitoloDiVIaggio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.InputMismatchException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import Enum.Periodicità;
import Main.GestioneTrasporti;
import Tessera.Tessera;
import lombok.Getter;

@Entity
@Table(name = "abbonamenti")
@DiscriminatorValue("Abbonamento")
public class Abbonamento extends TitoloDiVIaggio {

    @Enumerated(EnumType.STRING)
    private Periodicità periodicità;
    private Boolean validità;
    private @Getter LocalDate scadenzaAbbonamento;
    @ManyToOne
    private Tessera id_tessera;
    
    // Costruttori

    public Abbonamento() {
    }

    public Abbonamento(Boolean validità) {
	super();
	this.validità = validità;
	this.setPeriodicità();
    }

    public Abbonamento(Periodicità periodicità, Boolean validità) {
	super();
	this.periodicità = periodicità;
	this.validità = validità;
    }

    // Getter and Setter

    public Periodicità getPeriodicità() {
	return periodicità;
    }

    public void setPeriodicità() {
	int uscita = 0;
	while (uscita <= 0 || uscita >= 4) {
	    System.out.println(">> Seleziona la durata dell'abbonamento 1 = Settimanale 2 = Mensile");
	    try {
		uscita = GestioneTrasporti.s.nextInt();
		GestioneTrasporti.s.nextLine();
		switch (uscita) {
		case 1:
		    this.periodicità = Periodicità.SETTIMANALE;
		    break;
		case 2:
		    this.periodicità = Periodicità.MENSILE;
		    break;
		}
	    } catch (InputMismatchException e) {
		System.out.println("Utilizza un valore valido");
		GestioneTrasporti.s.nextInt();
	    }

	}
    }

    public Boolean getValidità() {
	return validità;
    }

    public void setScadenzaAbbonamento() {
	LocalDate d;

	d = this.dataemissione.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	if (getPeriodicità().equals(Periodicità.SETTIMANALE)) {
	    this.scadenzaAbbonamento = d.plusWeeks(1);
	} else {
	    this.scadenzaAbbonamento = d.plusMonths(1);
	}

    }

}
