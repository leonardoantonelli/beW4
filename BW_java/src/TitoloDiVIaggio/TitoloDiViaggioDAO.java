package TitoloDiVIaggio;

import javax.persistence.EntityManager;
import JpaUtil.JpaUtil;

public class TitoloDiViaggioDAO {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	public static void saveTitoloViaggio(TitoloDiVIaggio t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }
	
}
