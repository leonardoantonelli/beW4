package Tessera;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import JpaUtil.JpaUtil;
import Mezzi.MezzoDiTrasporto;
import Tratte.Tratta;
import Tratte.TrattaDAO;

public class TesseraDAO {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	public static void saveTessera(Tessera t) {
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			System.out.println("Tessera n " + t.getId() + " creata correttamente! \n");
		} catch (Exception e) {System.out.println("Error" + e);}
	}
	
	public static Tessera findTessera(Long id) {
		  em.getTransaction().begin();
	      Tessera t = em.find(Tessera.class, id);
	      em.getTransaction().commit();
	      return t;
	}
	
	public static long isTessereEmpty () {
		
		Query q = em.createQuery("Select Count(*) from Tessera");
	    long count = (long) q.getSingleResult();
	    return count;
		
	}
	
	
}
