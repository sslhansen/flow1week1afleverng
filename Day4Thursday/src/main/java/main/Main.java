/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Sebastian
 */
public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Employee("Svend Klausen", "Skodvej 1", 99999));
        em.persist(new Employee("Svend Klausen", "Skodvej 2", 99999));
        em.persist(new Employee("Svend Klausen", "Skodvej 3", 99999));
        em.persist(new Employee("Svend Klausen", "Skodvej 4", 99999));
        em.persist(new Employee("Svend Klausen", "Skodvej 5", 99999));
        em.persist(new Employee("Svend Klausen", "Skodvej 6", 99999));
        em.getTransaction().commit();
        em.close();
    }
}
