package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee emp = em.find(Employee.class, id);
            return emp;
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
            return query.setParameter("name", name).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee getEmployeeWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery(
                            "SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)", Employee.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Employee createEmployee(String name, String address, int salary) {
        Employee emp = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
            return emp;
        } finally {
            em.close();
        }
    }

}
