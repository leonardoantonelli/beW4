package Mezzi;

import java.util.Date;

import javax.persistence.*;


import Enum.Stato_mezzo;
@Entity
@Table(name= "titoli_di_viaggio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoMezzo", discriminatorType = DiscriminatorType.STRING)
public class MezziDiTrasporto {
	@Id	
 private Long id;
	@Enumerated(EnumType.STRING)
	private Stato_mezzo stato;
	
	private Date datainziotratta;
	private Date datafinetratta;
	
	private Date datainziomanutenzione;
	private Date datafinemanutenzione;
	
	
}
