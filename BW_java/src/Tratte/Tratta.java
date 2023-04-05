package Tratte;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import Main.GestioneTrasporti;
import Mezzi.MezzoDiTrasporto;

@Entity
@Table(name = "tratte")
public class Tratta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tratta_id;
	private String zonaDiPartenza;
	private String capolinea;
	private String tempoMedioDiPercorrenza;
	@ManyToOne
	@JoinColumn(name = "mezzo_id")
	private MezzoDiTrasporto mezzo;
	private int tot;
	
	//Costruttori
	
	public Tratta() {
		super();
		this.tot = 0;
	}

	public Tratta(Long tratta_id, String zonaDiPartenza, String capolinea, String tempoMedioDiPercorrenza,
			MezzoDiTrasporto mezzo, int tot) {
		super();
		this.tratta_id = tratta_id;
		this.zonaDiPartenza = zonaDiPartenza;
		this.capolinea = capolinea;
		this.tempoMedioDiPercorrenza = tempoMedioDiPercorrenza;
		this.mezzo = mezzo;
		this.tot = tot;
	}
	
	//Getter and Setter

	public Long getTratta_id() {
		return tratta_id;
	}

	public void setTratta_id(Long tratta_id) {
		this.tratta_id = tratta_id;
	}

	public String getZonaDiPartenza() {
		return zonaDiPartenza;
	}

	public void setZonaDiPartenza() {
		System.out.println("Inserisci la zona di partenza");
		this.zonaDiPartenza = GestioneTrasporti.s.nextLine();
	}

	public String getCapolinea() {
		return capolinea;
	}

	public void setCapolinea() {
		System.out.println("Inserisci il capolinea");
		this.capolinea = GestioneTrasporti.s.nextLine();
	}

	public String getTempoMedioDiPercorrenza() {
		return tempoMedioDiPercorrenza;
	}

	public void setTempoMedioDiPercorrenza() {
		System.out.println("Inserisci il tempo medio di percorrenza : ore (es 1)");
		String ore = GestioneTrasporti.s.nextLine();
		System.out.println("Inserisciil tempo medio di percorrenza : minuti (es 12)");
		String minuti = GestioneTrasporti.s.nextLine();
		try {
			this.tempoMedioDiPercorrenza = ore + ":" + minuti;
		} catch (Exception e) 
		{System.out.println("Error " + e);}
	}

	public MezzoDiTrasporto getMezzo() {
		return mezzo;
	}

	public void setMezzo(MezzoDiTrasporto mezzo) {
		this.mezzo = mezzo;
	}
	
	public static void toString(Tratta tra) {
		System.out.println("Tratta numero: " + tra.getTratta_id() + ", Partenza: " + tra.getZonaDiPartenza() + ", Capolinea: " + tra.getCapolinea() + ", Durata: " + tra.getTempoMedioDiPercorrenza() + " h \n");
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int t, int r) {
		this.tot = t + r;
	}
	
}
