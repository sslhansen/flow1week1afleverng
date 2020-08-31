package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeFacadeTest {

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu");
    private static final EmployeeFacade EF = EmployeeFacade.getFacadeExample(EMF);

    public EmployeeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
//        Add code to setup entities for test before running any test methods

    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        EMF.close();
    }

    @BeforeEach
    public void setUp() {
//        Put the test database in a proper state before each test is run
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("DELETE FROM Employee");
        q.executeUpdate();
        em.getTransaction().commit();
        Employee e1 = new Employee("Svend", "Tysklandsboulevard 1", 20000);
        Employee e2 = new Employee("Henrik", "Sverigevej 9", 25000);
        Employee e3 = new Employee("John", "Johnvej 91", 90000);
        Employee e4 = new Employee("Torkild", "PustÆgVej 21", 15000);
        em.getTransaction().begin();
        em.persist(e1);
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.persist(e2);
        em.persist(e3);
        em.persist(e4);
        em.getTransaction().commit();
        em.close();
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * PROBLEM MED DEN HER!!!!!!!
     */
    @Test
    public void testGetEmployeeById() {
        EntityManager em = EMF.createEntityManager();
        Employee svend = new Employee("klaus", "trækkerstræde 9", 90000);
        Employee emp = EF.getEmployeeById(5L);
        String expectedName = "Svend";
        assertEquals(expectedName, emp.getName());
    }

    @Test
    public void testGetEmployeesByName() {
        List<Employee> emps = EF.getEmployeesByName("Svend");
        String expectedName = "Svend";
        for (Employee emp : emps) {
            assertEquals(expectedName, emp.getName());
        }
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> emps = EF.getAllEmployees();
        int expectedSize = 4;
        assertEquals(expectedSize, emps.size());
    }

    @Test
    public void testGetEmployeesWithHighestSalary() {
        Employee emp = EF.getEmployeeWithHighestSalary();
        int expectedSalary = 90000;
        assertEquals(expectedSalary, emp.getSalary());
    }

    @Test
    public void testCreateEmployee() {
        int sizeBefore = EF.getAllEmployees().size();
        EF.createEmployee("HamDenNye", "Grimvej 92", 30111);
        int sizeNow = EF.getAllEmployees().size();
        assertEquals(sizeBefore + 1, sizeNow);
    }

}
