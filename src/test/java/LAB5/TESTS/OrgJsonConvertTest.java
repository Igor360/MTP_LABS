package LAB5.TESTS;

import LAB5.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class OrgJsonConvertTest {
    private  boolean isCreatedFile = false;
    private Employee employee =  new Employee("f","f","f", "+380123456789","f");
    private Employeer employeer = new Employeer("s","s","s","+380123456789","s");
    private Company company = new Company("d", "d","U");
    private Employeer readEmploeer = null;


    @Before
    public void init(){
        try{
            this.employeer.addEmployee(employee);
            this.employeer.setCompany(this.company);
            OrgJsonConvert.toJSON(this.employeer);
            this.isCreatedFile = true;
            this.readEmploeer = OrgJsonConvert.toJavaObject();
        }catch (IOException ex){
            this.isCreatedFile = false;
        }
    }

    @Test
    public void testingClass(){
        Assert.assertEquals(this.isCreatedFile, true);
        Assert.assertTrue(this.readEmploeer != null);
    }
}
