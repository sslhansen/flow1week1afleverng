package rest;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employee")
public class EmployeeFromDB {

    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getFacadeExample(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id") int id) {
        Employee emp = facade.getEmployeeById(id);
        EmployeeDTO empDto = new EmployeeDTO(emp);
        return new Gson().toJson(empDto);
    }

    @GET
    @Path("/all")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        List<Employee> emps = facade.getAllEmployees();
        List<EmployeeDTO> empsDto = new ArrayList();
        for (Employee emp : emps) {
            EmployeeDTO empDto = new EmployeeDTO(emp);
            empsDto.add(empDto);
        }
        return new Gson().toJson(empsDto);
    }

    @GET
    @Path("/highestpaid")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getHighestPaid() {
        Employee emp = facade.getEmployeeWithHighestSalary();
        EmployeeDTO empDto = new EmployeeDTO(emp);
        return new Gson().toJson(empDto);
    }

    @GET
    @Path("/name/{name}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getEmployeesByName(@PathParam("name") String name) {
        List<Employee> emps = facade.getEmployeesByName(name);
        List<EmployeeDTO> empsDto = new ArrayList();
        for (Employee emp : emps) {
            EmployeeDTO empDto = new EmployeeDTO(emp);
            empsDto.add(empDto);
        }
        return new Gson().toJson(empsDto);
    }

}
