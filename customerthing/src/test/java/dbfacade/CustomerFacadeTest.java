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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Sebastian
 */
public class CustomerFacadeTest {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private CustomerFacade fc = CustomerFacade.getCustomerFacade(emf);

    public CustomerFacadeTest() {
    }

    @BeforeAll
    public void setUp() {
    }

    @Test
    public void testFindByID() {
        fc.addCustomer("Svend", "henriksen");
        Customer cm = fc.findByID(1);
        assertEquals("svend", cm.getFirstName().toLowerCase());
    }

    @Test
    public void testFindByLastName() {
        List<Customer> cms = fc.findByLastName("henrik");
        for (Customer cm : cms) {
            assertEquals("henrik", cm.getLastName());
        }
    }

    @Test
    public void testGetNumberOfCustomers() {
        List<Customer> cm = fc.allCustomers();
        int number = fc.getNumberOfCustomers();
        assertEquals(cm.size(), number);
    }

    @Test
    public void testAllCustomers() {
        List<Customer> cm = fc.allCustomers();
        int number = fc.getNumberOfCustomers();
        assertEquals(cm.size(), number);
    }

    @Test
    public void testAddCustomer() {
        int sizeBefore = fc.getNumberOfCustomers();
        fc.addCustomer("svend", "klausen");
        int sizeAfter = fc.getNumberOfCustomers();
        assertEquals(sizeBefore + 1, sizeAfter);
    }

}
