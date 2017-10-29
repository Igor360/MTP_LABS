package LAB5.TESTS;

import LAB5.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class CompanyTest {
    private Company comapany = null;

    @Before
    public void init(){
        this.comapany = new Company("d", "d","U");
    }

    @Test
    public void testClass(){
        Assert.assertEquals(this.comapany.name,"d");
        Assert.assertEquals(this.comapany.address, "d");
        Assert.assertEquals(this.comapany.phone, "U");
    }
}
