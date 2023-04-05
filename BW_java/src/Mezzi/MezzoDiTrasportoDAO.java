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
	
}
