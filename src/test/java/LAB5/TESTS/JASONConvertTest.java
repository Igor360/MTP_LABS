package LAB5.TESTS;

import LAB5.Employee;
import LAB5.Employeer;
import LAB5.GoogleJsonConvert;
import LAB5.JASONConvert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class JASONConvertTest {
    private  boolean isCreatedFile = false;
    private Employee employee =  new Employee("f","f","f", "+380123456789","f");
    private Employeer employeer = new Employeer("s","s","s","+380123456789","s");
    {
        this.employeer.addEmployee(employee);
    }

    private Employeer readEmploeer = null;

    @Before
    public void init(){
        try{
            JASONConvert.toJSON(this.employeer);
            this.isCreatedFile = true;
            this.readEmploeer = JASONConvert.toJavaObject();
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
