package LAB9.TESTS;

import LAB9.Connector.PostgreSqlJDBC;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.ResultSet;

public class PostgresSqlJDBCTEST {
    PostgreSqlJDBC con = null;
    boolean is_connected = false;
    boolean is_query_executed = false;
    boolean is_closed = false;
    Connection conection  = null;
    ResultSet result = null;

    @Before
    public void init(){
        try {
            con = new PostgreSqlJDBC();
            is_connected = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (is_connected){
            try {
                con.executeQuery("Select * from users");
                con.executeQueryWithResult("Select * from users");
                is_query_executed = true;
            } catch (Exception e) {
                is_query_executed = false;
                e.printStackTrace();
            }
            conection = con.getConnect();
            result = con.getResultQuery();
        }


    }

    @Test
    public void test(){
        Assert.assertTrue(is_connected);
        Assert.assertTrue(is_query_executed);
        Assert.assertNotNull(conection);
        Assert.assertNotNull(result);
    }
}
