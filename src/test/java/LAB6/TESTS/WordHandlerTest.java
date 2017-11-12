package LAB6.TESTS;

import LAB6.ReadFile;
import LAB6.WordHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WordHandlerTest {

    ReadFile file = null;
    boolean is_read = false;
    String result = null;
    String result2 = null;
    @Before
    public void init(){
        try {
            file = new ReadFile("text.txt");
            file.read();
            is_read = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = WordHandler.getLongestConcatenatedWord(file.getDictionaryWords());
        result2 = WordHandler.getLongestConcatenatedWord2();
    }

    @Test
    public void test(){
        Assert.assertEquals(result,"ratcatdogcat");
        Assert.assertEquals(result2, "catsdogcats");
    }
}
