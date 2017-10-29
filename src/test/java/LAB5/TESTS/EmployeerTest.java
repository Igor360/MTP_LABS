package LAB5.TESTS;

import LAB5.Employee;
import LAB5.Employeer;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeerTest {
    private Employeer employeer = null;
    private Employee employee = null;

    @Before
    public void init(){
        this.employeer = new Employeer("s","s","s","+380123456789","s");
        this.employee = new Employee("f","f","f", "+380123456789","f");
    }

    @Test
    public void testingClass(){
        Assert.assertEquals(this.employeer.firstName, "s");
        Assert.assertEquals(this.employeer.secondName, "s");
        Assert.assertEquals(this.employeer.lastName, "s");
        Assert.assertEquals(this.employeer.number, "+380123456789");
        Assert.assertEquals(this.employeer.address, "s");
        this.employeer.addEmployee(employee);
        Assert.assertTrue(this.employeer.getEmployees().size() > 0);
        Assert.assertEquals(this.employeer.getEmployees().get(0), this.employee);

    }
}
