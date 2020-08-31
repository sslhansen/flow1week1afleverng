package dto;

import entities.Employee;

/**
 *
 * @author Sebastian
 */
public class EmployeeDTO {

    private Long id;
    private String name;
    private String address;

    public EmployeeDTO(Employee emp) {
        id = emp.getId();
        name = emp.getName();
        address = emp.getAddress();
    }

}
