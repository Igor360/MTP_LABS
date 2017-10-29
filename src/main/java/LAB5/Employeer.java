package LAB5;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Employeer extends Worker {
    @JsonProperty("employees")
    private  ArrayList<Employee> employees = new ArrayList<Employee>();

    @JsonProperty("company")
    private Company company;

    public Employeer(){
        super();
    }
    public Employeer(String firstName, String secondName, String lastName, String number, String address){
        super(firstName, secondName, lastName, number, address);
    }
    public ArrayList<Employee> getEmployees(){
        return this.employees;
    }

    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }

    public void deleteEmployee(Employee employee){
        this.employees.remove(employee);
    }

    public void setCompany (Company company){
        this.company = company;
    }

    public Company getCompany(){
        return company;
    }
}
