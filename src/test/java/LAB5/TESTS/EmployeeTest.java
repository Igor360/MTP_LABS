package LAB5.TESTS;

import LAB5.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
    private Employee employee = null;

    @Before
    public void init(){
        this.employee = new Employee("f","f","f", "+380123456789","f");
    }

    @Test
    public void testClass(){
        Assert.assertEquals(this.employee.firstName, "f");
        Assert.assertEquals(this.employee.secondName, "f");
        Assert.assertEquals(this.employee.lastName, "f");
        Assert.assertEquals(this.employee.number, "+380123456789");
        Assert.assertEquals(this.employee.address, "f");
    }
}