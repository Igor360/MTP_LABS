package LAB8;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {
    private String url = "https://api.github.com/repositories";
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private  HttpClientContext context = HttpClientContext.create();
    private HttpGet httpget = new HttpGet(url);
    private CloseableHttpResponse response = null;

    public Client() throws Exception{
        httpget = new HttpGet(url);
        httpget.addHeader("Accept", "application/vnd.github.full+json");

    }

    public Client(String query){
        httpget = new HttpGet(query);
        httpget.addHeader("Accept", "application/vnd.github.full+json");
    }

    public void executeQuery() throws IOException {
        response = httpclient.execute(httpget);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }




}
