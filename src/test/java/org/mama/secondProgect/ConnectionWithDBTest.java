//package org.mama.secondProgect;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.sql.*;
//
//public class ConnectionWithDBTest {
//
//    @Test
//    public void testShouldGetConnection() throws SQLException {
//        Connection connection = DriverManager.getConnection(Starter.DB_URL, "postgres", "12345");
//        Assert.assertTrue(connection.isValid(1));
//        Assert.assertFalse(connection.isClosed());
//    }
//
//    @Test
//    public void testShouldGetStatement() throws SQLException {
//        Connection connection = DriverManager.getConnection(Starter.DB_URL, "postgres", "12345");
//        Statement statement = connection.createStatement();
//        statement.executeUpdate("CREATE TABLE test_table(test int);");
//        statement.executeUpdate("INSERT INTO test_table(test) VALUES (6);");
//        Assert.assertTrue(statement.execute("SELECT * FROM test_table;"));
//        Assert.assertFalse(statement.isClosed());
//        statement.executeUpdate("DROP TABLE test_table;");
//        connection.close();
//        statement.close();
//    }
//
//    @Test
//    public void testExecuteUpdate() throws SQLException {
//        Connection connection = DriverManager.getConnection(Starter.DB_URL, "postgres", "12345");
//        Statement statement = connection.createStatement();
//        statement.executeUpdate("CREATE TABLE test_table(test int);");
//        statement.executeUpdate("INSERT INTO test_table(test) VALUES (6);");
//        ResultSet rs = statement.executeQuery("SELECT * FROM test_table;");
//        int i = 0;
//        while (rs.next()) {
//            i = rs.getInt(1);
//        }
//        Assert.assertEquals(i, 6);
//        statement.executeUpdate("DROP TABLE test_table;");
//        connection.close();
//        statement.close();
//    }
//
//}
