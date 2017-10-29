package LAB5.TESTS;

import LAB5.WorkInterface;
import org.junit.Assert;
import org.junit.Test;

public class InterfaceTest {

    @Test
    public void testingClass(){
        String time = WorkInterface.changeTimeFormat((long)123456);
        Assert.assertEquals(time, "123 456 ");
    }

}
