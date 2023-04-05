package Mezzi;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;


import Enum.Stato_mezzo;
import Tratte.Tratta;
@Entity
@Table(name= "mezzi_di_trasporto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoMezzo", discriminatorType = DiscriminatorType.STRING)
public class MezzoDiTrasporto {
	
	@Id	
	private String mezzo_id;

	private Boolean stato_manutenzione;
	private Integer numeroPosti;
	private Date datainziomanutenzione;
	private Date datafinemanutenzione;
	@OneToMany(mappedBy = "mezzo")
	private Set<Tratta> tratte;
	
	//Costruttori
	
	public MezzoDiTrasporto(String mezzo_id, Boolean stato_manutenzione, Date datainziotratta, Date datafinetratta,
			Date datainziomanutenzione, Date datafinemanutenzione, Set<Tratta> tratte) {
		super();
		this.mezzo_id = mezzo_id;
		this.stato_manutenzione = stato_manutenzione;
		//this.datainziotratta = datainziotratta;
		//this.datafinetratta = datafinetratta;
		this.datainziomanutenzione = datainziomanutenzione;
		this.datafinemanutenzione = datafinemanutenzione;
		this.tratte = tratte;
	}

	public MezzoDiTrasporto() {
		super();
	}

	//Getter and Setter
	
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

	public void setStatoManutenzione(Boolean stato_manutenzione) {
		this.stato_manutenzione = stato_manutenzione;
	}

	//public Date getDatainziotratta() {
	//	return datainziotratta;
	//}

	//public void setDatainziotratta(Date datainziotratta) {
	//	this.datainziotratta = datainziotratta;
	//}

	//public Date getDatafinetratta() {
	//	return datafinetratta;
	//}

	//public void setDatafinetratta(Date datafinetratta) {
	//	this.datafinetratta = datafinetratta;
	//}

	public Date getDatainziomanutenzione() {
		return datainziomanutenzione;
	}

	public void setDatainziomanutenzione(Date datainziomanutenzione) {
		this.datainziomanutenzione = datainziomanutenzione;
	}

	public Date getDatafinemanutenzione() {
		return datafinemanutenzione;
	}

	public void setDatafinemanutenzione(Date datafinemanutenzione) {
		this.datafinemanutenzione = datafinemanutenzione;
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
		if(m instanceof Autobus) {
			this.numeroPosti = 30;
		} else {
			this.numeroPosti = 60;
		}
	}
	
	public static String generateLetters(int f) {
		String letters = "";
		int n = 'Z' - 'A' + 1;
		for(int i = 0; i < f; i++) {
			char c = (char) ('A' + Math.random() * n);
			letters += c;
		}
		return letters;
	}
	
	public static String generateNumbers(int f) {
		String digits = "";
		int n = '9' - '0' + 1;
		for(int i = 0; i < f; i++) {
			char c = (char) ('0' + Math.random() * n);
		digits += c;
		}
		return digits;
	}
	
	
}