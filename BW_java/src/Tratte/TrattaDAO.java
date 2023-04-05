package Tratte;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import JpaUtil.JpaUtil;



public class TrattaDAO {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	public static void saveTratta(Tratta t) {
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			System.out.println("Tratta n " + t.getTratta_id() + " creata correttamente! \n");
		} catch (Exception e) {System.out.println("Error" + e);}
	}
	
	public static Tratta findTratta (Long numeroTratta) {
		Tratta tratta = null;
		try {
			em.getTransaction().begin();
			tratta = em.find(Tratta.class, numeroTratta);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return tratta;
	}
	
	public static long isTratteEmpty() {
		
		Query q = em.createQuery("SELECT Count(*) FROM Tratta");
	    long count = (long) q.getSingleResult();
	    return count;
	
	}
	
	public static List<Tratta> getAllTratte () {
		List<Tratta> list = null;
		try {
			Query q = em.createQuery("SELECT t FROM Tratta t");
			list = q.getResultList();
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
		return list;
	}
	
	public static List<Tratta> getListByMezzoID (String s) {
		
		List<Tratta> list = null;
		try {
			Query q = em.createQuery("SELECT t FROM Tratta t WHERE mezzo_id = :id ");
			q.setParameter("id", s);
			list = q.getResultList();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return list;
	}
	
	public static void modificaTratta (Tratta t) {
		try {
			em.getTransaction().begin();
		    em.merge(t);
			em.getTransaction().commit();
	
		} catch (Exception e) {System.out.println("Error" + e);}
	}
	
}
