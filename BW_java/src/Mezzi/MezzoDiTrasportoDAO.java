package Mezzi;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import JpaUtil.JpaUtil;


public class MezzoDiTrasportoDAO {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	public static void saveMezzo(MezzoDiTrasporto m) {
		try {
			em.getTransaction().begin();
			em.persist(m);
			em.getTransaction().commit();
			System.out.println("Mezzo n " + m.getMezzo_id() + " aggiunto correttamente! \n");
		} catch (Exception e) {System.out.println("Error" + e);}
	}
	
	public static Long isMezziEmpty() {
		Query q = em.createQuery("SELECT COUNT(*) FROM MezzoDiTrasporto");
		Long count =(Long) q.getSingleResult();
		return count;
	}
	
	
	public static MezzoDiTrasporto trovaMezzo(String s) {
		MezzoDiTrasporto mezzo =null;
		try {
		
			em.getTransaction().begin();
			mezzo= em.find(MezzoDiTrasporto.class,s);
			em.getTransaction().commit();
			
		} catch (Exception e) {System.out.println("Error" + e);}
		return mezzo;
	}
	
	public static void modificaMezzo(MezzoDiTrasporto m) {
		try {
			em.getTransaction().begin();
		    em.merge(m);
			em.getTransaction().commit();
	
		} catch (Exception e) {System.out.println("Error" + e);}
	}
}
