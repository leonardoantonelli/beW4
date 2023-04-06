package TitoloDiVIaggio;

import javax.persistence.EntityManager;

import JpaUtil.JpaUtil;


public class BigliettoDAO {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	public static void modificaBiglietto (Biglietto b) {
		try {
			em.getTransaction().begin();
		    em.merge(b);
			em.getTransaction().commit();
	
		} catch (Exception e) {System.out.println("Error" + e);}
	}
	
}
