package LAB8;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 *
 */
public class Client {
    private String url = "https://api.github.com/";
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private HttpClientContext context = HttpClientContext.create();
    private HttpGet httpget = new HttpGet(url);
    private CloseableHttpResponse response = null;
    private String jsonResult = null;

    public Client() throws Exception{
        httpget = new HttpGet(url);
        httpget.addHeader("Accept", "application/vnd.github.full+json");
    }

    public Client(String query, String acceptHeader){
        httpget = new HttpGet(query);
        httpget.addHeader("Accept", acceptHeader);
    }

    public void executeQuery() throws IOException {
        response = httpclient.execute(httpget);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            //System.out.println(line);
            jsonResult = line;
        }
    }

    public Map<String,Object> getJsonResult() throws  IOException{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map = mapper.readValue(jsonResult, new TypeReference<Map<String,Object>>(){});
        return map;
    }
    public ArrayList<Map<String, Object>> getJsonResultInArray() throws  IOException{
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
        map = mapper.readValue(jsonResult, new TypeReference<ArrayList<Map<String, Object>>>(){});
        return map;
    }

    public static void getListLargestStars() throws IOException{
        StringBuilder query= new StringBuilder("https://api.github.com/");
        query.append("search/repositories?q=stars:10000..*+pushed:2017-11-08..2017-11-19&sort=stars&order=desc");
        Client client = new Client(query.toString(), "application/vnd.github.mercy-preview+json");
        Map<String,Object> result = new HashMap<>();
        client.executeQuery();
        result = client.getJsonResult();
        ArrayList<Map<String, String>> arrayRepository =(ArrayList<Map<String, String>>) result.get("items");
        for (Map<String, String> repository: arrayRepository) {
                System.out.println(repository.get("full_name") +"\t stars: "+ String.valueOf(repository.get("stargazers_count"))+ "\t pushed_at: " + String.valueOf(repository.get("pushed_at")));
        }
    }

    public static void getListLargestCommited() throws IOException{
        StringBuilder query= new StringBuilder("https://api.github.com/");
        query.append("search/commits?q=committer-date:2017-11-08..2017-11-19&sort=committer-date&order=desc");
        Client client = new Client(query.toString(), "application/vnd.github.cloak-preview+json");
        Map<String,Object> result = new HashMap<>();
        client.executeQuery();
        result = client.getJsonResult();
        ArrayList<Map<String, Object>> arrayRepository =(ArrayList<Map<String, Object>>) result.get("items");
        for (Map<String, Object> commitRep: arrayRepository) {
                Map<String,String> repository = (Map<String,String>)commitRep.get("repository");
                System.out.println(repository.get("full_name") +"\t url address: "+ String.valueOf(repository.get("url"))+ "\t commits url : " + String.valueOf(repository.get("commits_url")));
        }

    }



    public static ArrayList<Map<String, Object>> getMostPopularRepos(){
        StringBuilder query= new StringBuilder("https://api.github.com/");
        query.append("search/repositories?q=stars:50000..*&sort=stars&order=desc&per_page=10");
        Client client = new Client(query.toString(), "application/vnd.github.mercy-preview+json");
        Map<String,Object> result = new HashMap<>();
        try {
            client.executeQuery();
            result = client.getJsonResult();
            ArrayList<Map<String, Object>> arrayRepository =(ArrayList<Map<String, Object>>) result.get("items");
            return arrayRepository;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getInformationAboutRepository() throws IOException{
       ArrayList<Map<String,Object>> repositories = getMostPopularRepos();
       StringBuilder query = null;
       Client client = null;
       ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
       Map<String,String> users = null;
        for (Map<String, Object> repository: repositories) {
            query = new StringBuilder("https://api.github.com/");
            query.append("repos/");
            query.append(repository.get("full_name"));
            query.append("/stats/contributors?order=desc");
            client = new Client(query.toString(), "application/vnd.github.v3+json");
            client.executeQuery();
            result = client.getJsonResultInArray();
            System.out.println(repository.get("full_name"));
            System.out.println("Description : " + repository.get("description"));
            System.out.println("Language : " + repository.get("language"));
            System.out.println("Users : ");
            for (Map<String,String> userData: getListContributors(result)) {
                    System.out.println(userData.get("login")+ "\t" + userData.get("commits_count"));
            }

        }
    }

    private static ArrayList<Map<String, String>> getListContributors(ArrayList<Map<String, Object>> arrayContributors){
        Map<String, String> user = null;
        ArrayList<Map<String, String>> result = new ArrayList<>();
        int size = arrayContributors.size();
        Map<String, Object> userCommits = null;
        Map<String,String> userData = null;
        for (int i = size-1; i >= size-5; i--){
            user = new HashMap<>();
            userCommits = arrayContributors.get(i);
            userData = (Map<String, String>) userCommits.get("author");
            user.put("login", userData.get("login"));
            user.put("url", userData.get("html_url"));
            user.put("commits_count",String.valueOf(numCommitsByUser((ArrayList<Map<String,Integer>>) userCommits.get("weeks"))));
            result.add(user);
        }
        return result;
    }



    public static int numCommitsByUser(ArrayList<Map<String,Integer>> commitData){
        int commits_count = 0;
        for(Map<String, Integer> weekData : commitData){
            commits_count+=weekData.get("c");
        }
        return commits_count;
    }





}
