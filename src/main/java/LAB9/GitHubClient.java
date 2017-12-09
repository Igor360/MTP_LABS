package LAB9;

import LAB8.Client;
import LAB9.Connector.PostgreSqlJDBC;
import LAB9.GitClasses.*;
import LAB9.Models.Owners;
import LAB9.Models.RepositoryData;

import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.*;

import static LAB8.Client.numCommitsByUser;

public class GitHubClient {

    private GitHubClient(){ }
    private static List<Repositories> repositoriesCollection = new ArrayList<>();
    private static Map<Repositories, List<Contributors>> contributorsDic = new HashMap<>();

    public static void getAllRepositories() throws IOException{
        StringBuilder query= new StringBuilder("https://api.github.com/");
        query.append("search/repositories?q=stars:10000..*+pushed:2017-11-08..2017-11-19&sort=stars&order=desc");
        Client client = new Client(query.toString(), "application/vnd.github.mercy-preview+json");
        client.executeQuery();
        Map<String, Object> repositories = client.getJsonResult();
        covertToObjects(repositories);
    }


    private static void covertToObjects(Map<String, Object> resultQuery){
        ArrayList<Map<String, Object>> listRepositories  = (ArrayList<Map<String,Object>>) resultQuery.get("items");
        for (Map<String, Object> rep : listRepositories){
            repositoriesCollection.add(generateRepository(rep));
        }
    }


    private static RepositoryOwners generateContributor(Map<String, Object> owner){
        return new RepositoryOwners(
                (Integer) owner.get("id"),
                (String) owner.get("login"),
                (String) owner.get("avatar_url"),
                (String) owner.get("url"),
                (String) owner.get("html_url"),
                (String) owner.get("repos_url"),
                (String) owner.get("type")
        );
    }


    private static Repositories generateRepository(Map<String, Object> repository){
        Map<String, Object> owner = (Map<String, Object>) repository.get("owner");
        return new Repositories(
                (Integer) repository.get("id"),
                (String) repository.get("name"),
                (String) repository.get("full_name"),
                generateContributor(owner),
                (Boolean) repository.get("private"),
                (String) repository.get("html_url"),
                (String) repository.get("description"),
                new Languages((String) repository.get("language"))
        );
    }



    public static void getInformationAboutRepository() throws IOException{
        ArrayList<Map<String,Object>> repositories = Client.getMostPopularRepos();
        StringBuilder query = null;
        Client client = null;
        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String,String> users = null;
        Contributors contributors = null;

        for (Map<String, Object> repository: repositories) {
            query = new StringBuilder("https://api.github.com/");
            query.append("repos/")
                    .append(repository.get("full_name"))
                    .append("/stats/contributors?order=desc");
            client = new Client(query.toString(), "application/vnd.github.v3+json");
            client.executeQuery();
            result = client.getJsonResultInArray();
            contributorsDic.put(
                        generateRepository(repository),
                        getListContributors(result)
            );
        }
    }



    private static List<Contributors> getListContributors(ArrayList<Map<String, Object>> arrayContributors){
        List<Contributors>  result = new ArrayList<>();
        int size = arrayContributors.size();
        Map<String, Object> userCommits = null;
        Contributors contributor = null;
        for (int i = size-1; i >= size - 6; i--){
            userCommits = arrayContributors.get(i);

            contributor = new Contributors(
                    generateContributor((Map<String, Object>) userCommits.get("author")),
                    numCommitsByUser((ArrayList<Map<String,Integer>>) userCommits.get("weeks"))
            );
            result.add(contributor);
        }
        return result;
    }


    public static void saveRepositiories() throws Exception{
        PostgreSqlJDBC con = new PostgreSqlJDBC();
        String query = null;
        for (Repositories repository : repositoriesCollection){
            query = QueryGenerator.insertIntoUsers(con.getConnect(),repository.getOwner());
            try {
                con.executeQuery(query);
            }catch (Exception ex){}
            query = QueryGenerator.insertIntoLanguages(con.getConnect(), repository.getLanguage());
            try {
                con.executeQuery(query);
            }catch (Exception ex){}
            query = QueryGenerator.insertIntoRepositories(con.getConnect(),repository);
            try {
                con.executeQuery(query);
            }catch (Exception ex){}
            query = QueryGenerator.insertIntoRepositoryOwners(con.getConnect(), repository);
            try {
                con.executeQuery(query);
            }catch (Exception ex){}
        }
    }

    public static void saveContributors() throws Exception{
        PostgreSqlJDBC con = new PostgreSqlJDBC();
        String query = null;
        Set<Repositories> repositories = contributorsDic.keySet();
        List<Contributors> contributors = null;
        for (Repositories repos : repositories){
            saveRepository(con, repos);
            contributors = contributorsDic.get(repos);
            for(Contributors contributor : contributors){
                query = QueryGenerator.insertIntoUsers(con.getConnect(), contributor.getUser());
                try {
                    con.executeQuery(query);
                }catch (Exception ex){}
                query = QueryGenerator.insertIntoContributors(con.getConnect(), contributor, repos);
                try {
                    con.executeQuery(query);
                }catch (Exception ex){}
            }
        }
    }


    private static void saveRepository(PostgreSqlJDBC con, Repositories repository) throws Exception{
        String query = null;
        query = QueryGenerator.insertIntoUsers(con.getConnect(),repository.getOwner());
        try {
            con.executeQuery(query);
        }catch (Exception ex){}
        query = QueryGenerator.insertIntoLanguages(con.getConnect(), repository.getLanguage());
        try {
            con.executeQuery(query);
        }catch (Exception ex){}
        query = QueryGenerator.insertIntoRepositories(con.getConnect(),repository);
        try {
            con.executeQuery(query);
        }catch (Exception ex){}
        query = QueryGenerator.insertIntoRepositoryOwners(con.getConnect(), repository);
        try {
            con.executeQuery(query);
        }catch (Exception ex){}
    }

    public static void getRepositoryOwner() throws Exception{
        PostgreSqlJDBC con = new PostgreSqlJDBC();
        con.executeQueryWithResult(QueryGenerator.getUsersRepositories());
        ResultSet res = con.getResultQuery();
        List<Owners> owners = new ArrayList<>();
        while (res.next()){
            owners.add(new Owners(res.getString(1), res.getString(2)));
        }
        for (Owners o : owners){
            System.out.println(o.toString());
        }
    }


    public static void getRepositoryLanguage() throws Exception{
        PostgreSqlJDBC con = new PostgreSqlJDBC();
        con.executeQueryWithResult(QueryGenerator.getRepositoryLang());
        ResultSet res = con.getResultQuery();
        List<RepositoryData> repos = new ArrayList<>();
        while (res.next()){
            repos.add(new RepositoryData(res.getString(1), res.getString(2)));
        }
        for (RepositoryData rep : repos){
            System.out.println(String.format("Name repository: %s \t language: %s ",rep.getName(), rep.getLang()));
        }
    }


    public static void getRepositoryContributors() throws Exception{
        PostgreSqlJDBC con = new PostgreSqlJDBC();
        con.executeQueryWithResult(QueryGenerator.getContributorsRepository());
        ResultSet res = con.getResultQuery();
        Map<String,List<String>> repContributors = new HashMap<>();
        while (res.next()){
            putInDictionary(repContributors, res.getString(1), res.getString(2));
        }
        Set<String> repositories = repContributors.keySet();
        for (String rep : repositories){
            System.out.println(String.format("Name repository: %s \t Users:",rep));
            for (String user : repContributors.get(rep)){
                System.out.println(user);
            }
        }
    }

    public static void getRepositoryNumContributors() throws Exception{
        PostgreSqlJDBC con = new PostgreSqlJDBC();
        con.executeQueryWithResult(QueryGenerator.getCommitsData());
        ResultSet res = con.getResultQuery();
        List<RepositoryData> repos = new ArrayList<>();
        while (res.next()){
            repos.add(new RepositoryData(res.getString(1), res.getString(2), res.getString(3)));
        }
        for (RepositoryData rep : repos){
            System.out.println(String.format("Name repository: %s \t Number contributors: %s \t Commits: %s", rep.getName(), rep.getNumContributors(), rep.getTotalCommits()));
        }
    }


    public static void getUserCommits() throws Exception{
        PostgreSqlJDBC con = new PostgreSqlJDBC();
        con.executeQueryWithResult(QueryGenerator.getCommitsUser());
        ResultSet res = con.getResultQuery();
        Map<String, String> usersCommits = new HashMap<>();
        while (res.next()){
            usersCommits.put(res.getString(1), res.getString(2));
        }
        Set<String> users = usersCommits.keySet();
        for (String user: users){
            System.out.println(String.format("Login: %s \t Number commits: %s", user, usersCommits.get(user)));
        }
    }


    private static void putInDictionary(Map<String,List<String>> repContributors, String nameRepos, String login){
        if (repContributors.containsKey(nameRepos)){
            repContributors.get(nameRepos).add(login);
        }else {
            List<String> users = new ArrayList<>();
            users.add(login);
            repContributors.put(nameRepos,users);
        }
    }
}
