package Distributori;

import Enum.Stato_distributore;

public class Distributore {

	private Stato_distributore stato;
	
	//Costruttore
	
	public Distributore() {
		this.stato = Stato_distributore.ATTIVO;
	}

	//Getter and Setter
	
	public Stato_distributore getStato() {
		return stato;
	}
	
	public void setStato(Stato_distributore s) {
		this.stato = s;
	}

	public static int verificaStato(Distributore d) {
		int opzionesalvata = 0;
		if(d.getStato().equals(Stato_distributore.ATTIVO)) {
			   opzionesalvata = 1;								
		} else {
			System.out.println("Distributore fuori servizio!");
		}
		return opzionesalvata;
	}
	
	public static void impostaStato (Distributore d) {
		if(d.getStato().equals(Stato_distributore.ATTIVO)) {
			d.setStato(Stato_distributore.FUORI_SERVIZIO);
			System.out.println("Il distributore è ora Fuori Servizio");
		} else {
			d.setStato(Stato_distributore.ATTIVO);
			System.out.println("Il distributore è ora Attivo");
		}
	}
}
