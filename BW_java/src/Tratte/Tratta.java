package Tratte;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import Mezzi.MezzoDiTrasporto;

@Entity
@Table
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
	
	//Costruttori
	
	public Tratta() {
		super();
	}

	public Tratta(Long tratta_id, String zonaDiPartenza, String capolinea, String tempoMedioDiPercorrenza,
			MezzoDiTrasporto mezzo) {
		super();
		this.tratta_id = tratta_id;
		this.zonaDiPartenza = zonaDiPartenza;
		this.capolinea = capolinea;
		this.tempoMedioDiPercorrenza = tempoMedioDiPercorrenza;
		this.mezzo = mezzo;
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

	public void setZonaDiPartenza(String zonaDiPartenza) {
		this.zonaDiPartenza = zonaDiPartenza;
	}

	public String getCapolinea() {
		return capolinea;
	}

	public void setCapolinea(String capolinea) {
		this.capolinea = capolinea;
	}

	public String getTempoMedioDiPercorrenza() {
		return tempoMedioDiPercorrenza;
	}

	public void setTempoMedioDiPercorrenza(String tempoMedioDiPercorrenza) {
		this.tempoMedioDiPercorrenza = tempoMedioDiPercorrenza;
	}

	public MezzoDiTrasporto getMezzo() {
		return mezzo;
	}

	public void setMezzo(MezzoDiTrasporto mezzo) {
		this.mezzo = mezzo;
	}
	
	
	
}
