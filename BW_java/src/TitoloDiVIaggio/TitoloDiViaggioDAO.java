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

    public static TitoloDiVIaggio trovaTitoloViaggio(Long s) {
	TitoloDiVIaggio v = null;
	try {
	    em.getTransaction().begin();
	    v = em.find(TitoloDiVIaggio.class, s);
	    em.getTransaction().commit();
	} catch (Exception e) {
	    System.err.println("error: " + e);
	}
	return v;

    }

}
