package LAB9.TESTS;


import LAB9.Connector.PostgreSqlJDBC;
import LAB9.GitClasses.*;
import LAB9.Models.RepositoryData;
import LAB9.QueryGenerator;
import org.junit.Assert;
import org.junit.Test;

public class QureryGeneratorTEST {


    @Test
    public void test(){
        PostgreSqlJDBC con = null;
        try {
            con = new PostgreSqlJDBC();
        }catch (Exception ex){}
        Users user = new Users(1111, "user", "1234", "wew", "sd", "sdd", "sdds");
        RepositoryOwners owner = new RepositoryOwners(1111, "user", "1234", "wew", "sd", "sdd", "sdds");
        Languages lang = new Languages("ru");
        Repositories repos = new Repositories(11,"sdd", "ds", owner, false, "dsdsd", "dsds",lang);
        Contributors cont = new Contributors(11, user, 111);
        Assert.assertNotNull(QueryGenerator.getCommitsData());
        Assert.assertNotNull(QueryGenerator.getCommitsUser());
        Assert.assertNotNull(QueryGenerator.getContributorsRepository());
        Assert.assertNotNull(QueryGenerator.getGenerateTableContributors());
        Assert.assertNotNull(QueryGenerator.getGenerateTableLanguages());
        Assert.assertNotNull(QueryGenerator.getGenerateTableRepositories());
        Assert.assertNotNull(QueryGenerator.getGenerateTableRepositoryOwners());
        Assert.assertNotNull(QueryGenerator.getGenerateTableUsers());
        Assert.assertNotNull(QueryGenerator.getRepositoryLang());
        Assert.assertNotNull(QueryGenerator.getUsersRepositories());
        Assert.assertNotNull(user);
        Assert.assertNotNull(lang);
        Assert.assertNotNull(repos);
        Assert.assertNotNull(cont);
        try {
            Assert.assertNotNull(QueryGenerator.insertIntoUsers(con.getConnect(), user));
            Assert.assertNotNull(QueryGenerator.insertIntoLanguages(con.getConnect(), lang));
            Assert.assertNotNull(QueryGenerator.insertIntoRepositoryOwners(con.getConnect(), repos));
            Assert.assertNotNull(QueryGenerator.insertIntoRepositories(con.getConnect(), repos));
            Assert.assertNotNull(QueryGenerator.insertIntoContributors(con.getConnect(), cont, repos));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
