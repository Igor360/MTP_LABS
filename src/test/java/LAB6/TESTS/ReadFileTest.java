package LAB6.TESTS;

import LAB6.ReadFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ReadFileTest {
    ReadFile file = null;
    boolean is_read = false;
    @Before
    public void init(){
        try {
            file = new ReadFile("text.txt");
            file.read();
            is_read = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        Assert.assertTrue(is_read);
        Assert.assertEquals(file.pathToFile,"text.txt");
        Assert.assertEquals(file.getDictionaryWords().size(), 6);
    }

}
