package Tessera;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Main.GestioneTrasporti;

@Entity
@Table(name = "tessere")
public class Tessera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private Date datadinascita;
	private Date dataemissione;
	private LocalDate datascadenza;
	private Boolean verificata;

	
	
	//Costruttori
	
	public Tessera() {}
	
	public Tessera(Boolean verificata) {
		this.setNome();
		this.setCognome();
		this.setDatadinascita();
		this.setDataemissione();
		this.setDatascadenza();
		this.verificata = verificata;
	}
	
	
	
	public Tessera(Long id, String nome, String cognome, Date dataemissione, LocalDate datascadenza) {
	this.id = id;
	this.nome = nome;
	this.cognome = cognome;
	this.dataemissione = dataemissione;
	this.datascadenza = datascadenza;
	}
	
	
	//Getter and Setter
	
	public String getNome() {
		return nome;
	}
	
	public void setNome() {
		System.out.println(">> Inserisci il tuo nome"); 
		this.nome = GestioneTrasporti.s.nextLine();
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome() {
		System.out.println(">> Inserisci il tuo cognome"); 
		this.cognome = GestioneTrasporti.s.nextLine();
	}
	public Date getDataemissione() {
		return dataemissione;
	}
	public void setDataemissione() {
		Date oggi = new Date();
		this.dataemissione = oggi;
	}
	public LocalDate getDatascadenza() {
		return datascadenza;
	}
	public void setDatascadenza() {
		LocalDate date = this.dataemissione.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate newdate = date.plusYears(1);
		this.datascadenza = newdate;
	}
	public Long getId() {
		return id;
	}

	public Boolean getVerificata() {
		return verificata;
	}

	public void setVerificata(Boolean verificata) {
		this.verificata = verificata;
	}

	public Date getDatadinascita() {
		return datadinascita;
	}

	public void setDatadinascita() {
		System.out.println(">> inserisci la tua data di nascita (DD/MM/YYYY)");
		String s = GestioneTrasporti.s.nextLine();
		
		while(true) {
			try {
				SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
				Calendar today = Calendar.getInstance();
				Date oggi =(Date) today.getTime();
				Date date = new Date(formatdate.parse(s).getTime());
				if(date.after(oggi)) {
					System.err.println("Non sapevo sapessi viaggiare nel tempo! Prova un data valida");
					s = GestioneTrasporti.s.nextLine();
				} else {				
					this.datadinascita = date;
					break;
				}
			} catch (Exception e) {
				System.out.println("Inserisci una data valida!");
				s = GestioneTrasporti.s.nextLine();
			}
		}
	}
}
