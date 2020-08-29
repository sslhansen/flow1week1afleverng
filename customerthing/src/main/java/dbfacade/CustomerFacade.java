/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sebastian
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer cm = em.find(Customer.class, id);
            return cm;
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT customer FROM Customer customer WHERE customer.lastName = :name", Customer.class);
            return query.setParameter("name", name).getResultList();
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT customer FROM Customer customer", Customer.class);
            return query.getResultList().size();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT customer FROM Customer customer", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Customer addCustomer(String fName, String lName) {
        EntityManager em = emf.createEntityManager();
        Customer cm = new Customer(fName, lName);
        try {
            em.getTransaction().begin();
            em.persist(cm);
            em.getTransaction().commit();
            return cm;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        Customer cm = facade.addCustomer("john", "hitler");
        facade.addCustomer("john", "hitler");
        facade.addCustomer("john", "hitler");
        facade.addCustomer("john", "hitler");

        Customer custe = facade.findByID(1);
        System.out.println(custe.getFirstName() + custe.getLastName());

        List<Customer> cust = facade.findByLastName("henrik");
        for (Customer c : cust) {
            System.out.println(c.getFirstName());
        }
        System.out.println(facade.getNumberOfCustomers());
        List<Customer> customers = facade.allCustomers();
        for (Customer c : customers) {
            System.out.println(c.getFirstName());
        }
        System.out.println(cm.getLastName());
    }

}
