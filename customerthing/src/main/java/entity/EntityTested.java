package entity;

import dbfacade.CustomerFacade;
import entity.Customer;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class EntityTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer cm1 = new Customer("john", "mogens");
        Customer cm2 = new Customer("svend", "henrik");
        Customer cm3 = new Customer("lars", "clausen");
        em.persist(cm1);
        em.persist(cm2);
        em.persist(cm3);
        em.getTransaction().commit();
        
    }
}
