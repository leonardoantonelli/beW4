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
	private Long mezzo_id;

	private Boolean stao;
	private Date datainziotratta;
	private Date datafinetratta;
	
	private Date datainziomanutenzione;
	private Date datafinemanutenzione;
	@OneToMany(mappedBy = "mezzo")
	private Set<Tratta> tratte;
	
	//Costruttori
	
	public MezzoDiTrasporto(Long mezzo_id, Boolean stao, Date datainziotratta, Date datafinetratta,
			Date datainziomanutenzione, Date datafinemanutenzione, Set<Tratta> tratte) {
		super();
		this.mezzo_id = mezzo_id;
		this.stao = stao;
		this.datainziotratta = datainziotratta;
		this.datafinetratta = datafinetratta;
		this.datainziomanutenzione = datainziomanutenzione;
		this.datafinemanutenzione = datafinemanutenzione;
		this.tratte = tratte;
	}

	public MezzoDiTrasporto() {
		super();
	}

	public Long getMezzo_id() {
		return mezzo_id;
	}

	public void setMezzo_id(Long mezzo_id) {
		this.mezzo_id = mezzo_id;
	}

	public Boolean getStao() {
		return stao;
	}

	public void setStao(Boolean stao) {
		this.stao = stao;
	}

	public Date getDatainziotratta() {
		return datainziotratta;
	}

	public void setDatainziotratta(Date datainziotratta) {
		this.datainziotratta = datainziotratta;
	}

	public Date getDatafinetratta() {
		return datafinetratta;
	}

	public void setDatafinetratta(Date datafinetratta) {
		this.datafinetratta = datafinetratta;
	}

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
	
	//Getter and Setter
	
	
}
