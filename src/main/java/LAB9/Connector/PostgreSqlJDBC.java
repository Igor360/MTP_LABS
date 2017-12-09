package LAB9.Connector;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSqlJDBC {

    @Getter
    private Connection connect = null;
    private Statement stmt = null;
    private ResultSet resultQuery = null;
    private StringBuilder url = new StringBuilder("jdbc:postgresql://localhost:5432/");
    private String bdName = "git_repos";
    private String username = "postgres";
    private String password = "1234";


    public PostgreSqlJDBC() throws Exception{
        Class.forName("org.postgresql.Driver");
        this.connect = DriverManager.getConnection(this.url.append(bdName).toString(), username, password);
        this.connect.getAutoCommit();
        this.stmt = connect.createStatement();
    }

    public void executeQuery(String query) throws Exception{
        this.stmt.executeQuery(query);
    }

    public void executeQueryWithResult(String query) throws Exception{
        resultQuery = this.stmt.executeQuery(query);
    }


    public ResultSet getResultQuery() throws NullPointerException{
        return resultQuery;
    }

    public void closeConnection() throws Exception{
        this.resultQuery.close();
        this.stmt.close();
        this.connect.commit();
        this.connect.close();
    }
}
