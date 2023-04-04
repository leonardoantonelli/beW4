package TitoloDiVIaggio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;

import javax.persistence.*;

import Enum.Ticket_Office;
import Main.GestioneTrasporti;


@Entity
@Table(name= "titoli_di_viaggio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Articolo", discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name = "Elementi.FindAll", query = "SELECT t FROM TitoloDiVIaggio t")
public class TitoloDiVIaggio {

	@Id
    private Long id;
	
	@Enumerated(EnumType.STRING)
    private Ticket_Office puntoemissione;

    private Date dataemissione;
    
 
    

    
	public TitoloDiVIaggio() {
		
		//this.setId();
		//this.setPuntoemissione();
		//this.setDataemissione();
	}		

	public TitoloDiVIaggio(Long id, Ticket_Office puntoemissione, Date dataemissione) {
		super();
		this.id = id;
		this.puntoemissione = puntoemissione;
		this.dataemissione = dataemissione;
	}

	//Setter and Getter
	
	public Long getId() {
		return id;
	}

	public void setId() {
		
		Long num = System.currentTimeMillis();
		Random rnum = new Random(num);
		Long isbn = Math.abs(rnum.nextLong()) % 9000000000000L + 1000000000000L;
		isbn *= 10L;
		this.id = isbn;
		
	}

	public Ticket_Office getPuntoemissione() {
		return puntoemissione;
	}

	public void setPuntoemissione(int o) {
		//int uscita = 0;
		//while(uscita <= 0 || uscita >= 4) {
			//System.out.println(">> Seleziona il punto di acquisto 1 = Distributore automatico 2 = Rivenditore autorizzato");
			try {
				//  uscita = GestioneTrasporti.s.nextInt();
				int uscita = o;
				switch(uscita){
				case 1:
					this.puntoemissione = Ticket_Office.DISTRIBUTORE_AUTOMATICO;
					break;
				case 2:
					this.puntoemissione = Ticket_Office.RIVENDITORE_AUTORIZZATO;
					break;
				}
			}catch(InputMismatchException e) {
				System.out.println("Error " + e);
			}
			
		//}
	}

	public Date getDataemissione() {
		return dataemissione;
	}

	public void setDataemissione() {
		System.out.println(">> inserisci la data di emissione (DD/MM/YYYY)");
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
					this.dataemissione = date;
					break;
				}
			} catch (Exception e) {
				System.out.println("Inserisci una data valida!");
				s = GestioneTrasporti.s.nextLine();
			}
		}
	
	}
	
	public static Date insertdata() {
		
		String s = GestioneTrasporti.s.nextLine();
		
		while(true) {
			try {
				SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
				Date oggi =new Date();
				Date date = new Date(formatdate.parse(s).getTime());
				if(date.after(oggi)) {
					System.err.println("La data per la verifica deve essere massimo oggi");
					s = GestioneTrasporti.s.nextLine();
				} else {				
				return	date;
				}
			} catch (Exception e) {
				System.out.println("Inserisci una data valida!");
				s = GestioneTrasporti.s.nextLine();
			}
		}
	}
}

