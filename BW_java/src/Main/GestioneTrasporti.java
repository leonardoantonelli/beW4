package Main;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import Enum.Stato_distributore;
import Enum.Ticket_Office;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Distributori.Distributore;
import JpaUtil.JpaUtil;
import Tessera.Tessera;
import Tessera.TesseraDAO;
import TitoloDiVIaggio.Abbonamento;
import TitoloDiVIaggio.Biglietto;
import TitoloDiVIaggio.TitoloDiVIaggio;
import TitoloDiVIaggio.TitoloDiViaggioDAO;

public class GestioneTrasporti {

	public static Scanner s = new Scanner(System.in); // Scanner da utilizzare ovunque

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

	public static void main(String[] args) {

		Distributore d1 = new Distributore();
		Distributore d2 = new Distributore();
		Distributore d3 = new Distributore();

		while (true) {
			System.out.println("Seleziona una delle seguenti opzioni:" + "\n 1 ACQUISTA TITOLO DI VIAGGIO " // Crea
																											// biglietti
																											// e
																											// abbonamenti
																											// solo se
																											// provvisti
																											// di numero
																											// tessera
					+ "\n 2 SOTTOSCRIVI TESSERA " // Crea una tessera
					+ "\n 3 GESTIONE BIGLIETTI/ABBONAMENTI " // Visualizza tutti i biglietti/abbonamenti emessi in un
																// range di date
					+ "\n 4 GESTIONE TESSERE " // Gestione stato distributori
					+ "\n 5 GESTIONE DISTRIBUTORI " // Verifica validità della tessera inserendo il numero e stampa
													// tutte le tessere
					+ "\n 6 GESTIONE MEZZI "); // Gestione manutenzione mezzi e creazione mezzi con assegnazione tratte

			int scelta = s.nextInt();
			s.nextLine();

			switch (scelta) {
			case 1:
				Boolean esci = false;
				while (!esci) {

					// Selezione punto vendita

					System.out.println(
							">> Seleziona un delle due opzioni di acquisto 1 = Distributore automatico 2 = Rivenditore | 0 per uscire.");
					int opzione = s.nextInt();
					s.nextLine();
					int opzionesalvata = 0;
					switch (opzione) {

					case 0:
						esci = true;
						break;

					// Selezione distributore e controllo stato

					case 1:
						System.out.println(
								">> Seleziona uno dei distributori Distributore = 1 Distributore = 2 Distributore = 3");
						int distributore = s.nextInt();
						s.nextLine();
						switch (distributore) {
						case 1:
							opzionesalvata = Distributore.verificaStato(d1);
							break;
						case 2:
							opzionesalvata = Distributore.verificaStato(d2);
							break;
						case 3:
							opzionesalvata = Distributore.verificaStato(d3);
							break;
						}
						break;

					// Seleziona Rivenditore

					case 2:
						opzionesalvata = 2;
						break;
					default:
						System.out.println("Prova uno valore valido");
					}

					// Verifica il risultato di opzione salvata

					if (opzionesalvata == 1 || opzionesalvata == 2) {
						System.out.println(
								">> Seleziona Titolo di viaggio da aggiungere 1 = Abbonamento 2 = Biglietto | 0 per uscire.");
						String agg1 = s.nextLine(); // Prende una stringa come input
						while (!esci) {
							try {
								int agg = Integer.parseInt(agg1); // Trasforma la stringa in int

								// Se uguale a 0 esce dal while

								if (agg == 0) {
									esci = true;

									// Se uguale a 1 Entra nella creazione dell' abbonamento

								} else if (agg == 1) {
									if (TesseraDAO.isTessereEmpty() == 0) {
										System.out.println(
												"Per gli abbonamenti è necessaria una tessera! Prima creane una!");
									break;
									} else {
										System.out.println(">> Inserisci un numero Tessera");
										Long numT = s.nextLong();
										s.nextLine();
										Tessera t = TesseraDAO.findTessera(numT);
										if (t != null && t.getId() != null && t.getId() == numT) {
											System.out.println("Benvenuto/a " + t.getNome());
											try {
												Abbonamento a = new Abbonamento(true);
												a.setId();
												a.setDataemissione();
												a.setPuntoemissione(opzionesalvata);
												TitoloDiViaggioDAO.saveTitoloViaggio(a);
												System.out.println("Abbonamento aggiunto con successo!");
												esci = true;
											} catch (Exception e) {
												System.out.println("Qualcosa è andatao storto! Error " + e);
											}
										} else {
											System.out.println("Numero tessera non trovato!");
										}
									}

									// Se uguale a 2 crea un Biglietto

								} else if (agg == 2) {
									try {
										Biglietto b = new Biglietto(false);
										b.setId();
										b.setDataemissione();
										b.setPuntoemissione(opzionesalvata);
										TitoloDiViaggioDAO.saveTitoloViaggio(b);
										System.out.println("Biglietto aggiunta con successo!");
										esci = true;
									} catch (Exception e) {
										System.out.println("Qualcosa è andatao storto");
									}
								}

								// Se l'input non è un numero torna un errore e richiede l'input

							} catch (NumberFormatException e) {
								System.out.println("Input Errato! Utilizza un numero");
								agg1 = s.nextLine();
							}
						}
					}
				}
				break;
			case 2:
				System.out.println("---- Creazione Tessera ----");
				Tessera t = new Tessera(true);
				TesseraDAO.saveTessera(t);
				break;
			case 3:
				boolean exit = false;
				while (!exit) {
					System.out.println("Seleziona una delle seguenti opzioni | 0 per uscire "
							+ "\n 1 VISUALIZZA TUTTI I TITOLI DI VIAGGIO"
							+ "\n 2 VISUALIZZA TITOLI DI VIAGGIO IN UN PERIODO DI TEMPO"
							+ "\n 3 VISUALIZZA TITOLI DI VIAGGIO PER PUNTO DI EMISSIONE");
					Integer ss = s.nextInt();
					s.nextLine();
					switch (ss) {
					case 0:
						exit = true;
						break;
					case 1:
						Query q = em.createNamedQuery("Elementi.FindAll");
						List<TitoloDiVIaggio> lista = q.getResultList();
						System.out
								.println("In totale sono stati emessi : " + lista.size() + " Biglietti/Abbonamenti \n");
						break;
					case 2:
						System.out.println(">> Inserisci la data da cui vuoi iniziare la ricerca");
						Date data = TitoloDiVIaggio.insertdata();
						System.out.println(">> Inserisci la data di fine riceerca");
						Date data2 = TitoloDiVIaggio.insertdata();
						Query q1 = em.createQuery(
								"SELECT t FROM TitoloDiVIaggio t WHERE t.dataemissione >= :d AND t.dataemissione <= :d1");
						q1.setParameter("d", data);
						q1.setParameter("d1", data2);
						List<TitoloDiVIaggio> lista1 = q1.getResultList();
						System.out.println(
								"In totale sono stati emessi : " + lista1.size() + " Biglietti/Abbonamenti \n");
						break;
					case 3:
						System.out.println(">> Seleziona il punto di emissione : \n" + " 1 Rivenditori autorizzati \n"
								+ " 2 Distributori automatici");
						String p = s.nextLine();
						try {
							int pnum = Integer.parseInt(p);
							switch (pnum) {
							case 1:
								Ticket_Office riv = Ticket_Office.RIVENDITORE_AUTORIZZATO;
								Query q2 = em
										.createQuery("SELECT t FROM TitoloDiVIaggio t WHERE t.puntoemissione = :riv");
								q2.setParameter("riv", riv);
								List<TitoloDiVIaggio> lista2 = q2.getResultList();
								System.out.println("In totale sono stati emessi : " + lista2.size()
										+ " Biglietti/Abbonamenti da Rivenditori Autorizzati \n");
								break;
							case 2:
								Ticket_Office dis = Ticket_Office.DISTRIBUTORE_AUTOMATICO;
								Query q3 = em
										.createQuery("SELECT t FROM TitoloDiVIaggio t WHERE t.puntoemissione = :dis");
								q3.setParameter("dis", dis);
								List<TitoloDiVIaggio> lista3 = q3.getResultList();
								System.out.println("In totale sono stati emessi : " + lista3.size()
										+ " Biglietti/Abbonamenti da Distributori Autorizzati \n");
								break;
							}
						} catch (NumberFormatException e) {
							System.out.println("Input Errato! Utilizza un numero");
							p = s.nextLine();
						}
						break;
					default:
						System.out.println("Scelta non valida");
					}
				}
				break;
//			case 4 :
//				public static List<Tessera> TesseraFindAll(){
//			        Query q4 = em.createNamedQuery("TesserefindAll");
//			        List<Tessera> list4 = q4.getResultList();
//			        return  list4;
//			    }
//				break;
			case 5:
				Boolean back = false;
				while (!back) {
					System.out.println(
							">> Seleziona il distibutore automatico da modificare: \n Distributore 1 - Distributore 2 - Distributore 3 | 0 per uscire");
					int opzione = s.nextInt();
					s.nextLine();
					switch (opzione) {
					case 0:
						back = true;
						break;
					case 1:
						Distributore.impostaStato(d1);
						break;
					case 2:
						Distributore.impostaStato(d2);
						break;
					case 3:
						Distributore.impostaStato(d3);
						break;
					default:
						System.out.println("Valore non valido");
					}
				}
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			default:
				System.out.println("Scelta non valida.");
			}
		}

	}

}
