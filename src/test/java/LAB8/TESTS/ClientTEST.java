package LAB8.TESTS;

import LAB8.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ClientTEST {
    Client client  = null;
    boolean isExecute = false;

    @Before
    public void  init(){
        client = new Client("https://api.github.com/", "application/vnd.github.mercy-preview+json");
    }

    @Test
    public void test(){
        try {
            client.executeQuery();
            Client.getInformationAboutRepository();
            Client.getListLargestCommited();
            Client.getListLargestStars();
            isExecute = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(isExecute);
    }
}
