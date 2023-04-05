package Mezzi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Main.GestioneTrasporti;
import Tratte.Tratta;

@Entity
@Table(name = "mezzi_di_trasporto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoMezzo", discriminatorType = DiscriminatorType.STRING)
public class MezzoDiTrasporto {

    @Id
    private String mezzo_id;

    private Boolean stato_manutenzione;
    private Integer numeroPosti;
    private LocalDate datainziomanutenzione;
    private LocalDate datafinemanutenzione;
    @OneToMany(mappedBy = "mezzo")
    private Set<Tratta> tratte;

    // Costruttori

    public MezzoDiTrasporto(String mezzo_id, Boolean stato_manutenzione, Date datainziotratta, Date datafinetratta,
	    LocalDate datainziomanutenzione, LocalDate datafinemanutenzione, Set<Tratta> tratte) {
	super();
	this.mezzo_id = mezzo_id;
	this.stato_manutenzione = stato_manutenzione;
	// this.datainziotratta = datainziotratta;
	// this.datafinetratta = datafinetratta;
	this.datainziomanutenzione = datainziomanutenzione;
	this.datafinemanutenzione = datafinemanutenzione;
	this.tratte = tratte;
    }

    public MezzoDiTrasporto() {
	super();
	this.stato_manutenzione = false;
    }

    // Getter and Setter

    public String getMezzo_id() {
	return mezzo_id;
    }

    public void setMezzo_id() {
	String targa;
	String letters = generateLetters(3);
	String digits = generateNumbers(8);
	targa = letters + digits;
	this.mezzo_id = targa;
    }

    public Boolean getStatoManutenzione() {
	return stato_manutenzione;
    }

    public void setStatoManutenzione(MezzoDiTrasporto m) {
	if (m.getStatoManutenzione()) {
	    this.stato_manutenzione = false;
	    m.setDatafinemanutenzione();
	    System.out.println("il veicolo uscirà dalla manutenzione in data: " + m.getDatafinemanutenzione());

	} else {
	    this.stato_manutenzione = true;
	    m.setDatainziomanutenzione();
	    System.out.println("il veicolo è ora in manutenzione");

	}

    }

    // public Date getDatainziotratta() {
    // return datainziotratta;
    // }

    // public void setDatainziotratta(Date datainziotratta) {
    // this.datainziotratta = datainziotratta;
    // }

    // public Date getDatafinetratta() {
    // return datafinetratta;
    // }

    // public void setDatafinetratta(Date datafinetratta) {
    // this.datafinetratta = datafinetratta;
    // }

    public LocalDate getDatainziomanutenzione() {
	return datainziomanutenzione;
    }

    public void setDatainziomanutenzione() {
	System.out.println(">> inserisci la data di inizio manutenzione (DD/MM/YYYY)");
	String s = GestioneTrasporti.s.nextLine();

	while (true) {
	    try {
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");

		Date oggi = new Date();
		Date date = new Date(formatdate.parse(s).getTime());
		if (date.after(oggi)) {
		    System.err.println("La data deve essere aggiornata max al giorno corrente");
		    s = GestioneTrasporti.s.nextLine();
		} else {
		    LocalDate date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    this.datainziomanutenzione = date1;
		    break;
		}
	    } catch (Exception e) {
		System.out.println("Inserisci una data valida!");
		s = GestioneTrasporti.s.nextLine();
	    }
	}
	// this.datainziomanutenzione = datainziomanutenzione;
    }

    public LocalDate getDatafinemanutenzione() {
	return datafinemanutenzione;
    }

    public void setDatafinemanutenzione() {
	this.datafinemanutenzione = getDatainziomanutenzione().plusWeeks(3);
    }

    public Set<Tratta> getTratte() {
	return tratte;
    }

    public void setTratte(Set<Tratta> tratte) {
	this.tratte = tratte;
    }

    public Integer getNumeroPosti() {
	return numeroPosti;
    }

    public void setNumeroPosti(MezzoDiTrasporto m) {
	if (m instanceof Autobus) {
	    this.numeroPosti = 30;
	} else {
	    this.numeroPosti = 60;
	}
    }

    public static String generateLetters(int f) {
	String letters = "";
	int n = 'Z' - 'A' + 1;
	for (int i = 0; i < f; i++) {
	    char c = (char) ('A' + Math.random() * n);
	    letters += c;
	}
	return letters;
    }

    public static String generateNumbers(int f) {
	String digits = "";
	int n = '9' - '0' + 1;
	for (int i = 0; i < f; i++) {
	    char c = (char) ('0' + Math.random() * n);
	    digits += c;
	}
	return digits;
    }

}
