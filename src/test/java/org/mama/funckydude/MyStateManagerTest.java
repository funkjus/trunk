//package org.mama.funckydude;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
//import javax.swing.event.ListDataEvent;
//import javax.swing.event.ListDataListener;
//import java.sql.*;
//
//public class MyStateManagerTest {
//
//@Before
//    public int CreateConnection() throws SQLException {
//    int test = 0;
//    try {
//        // This is where we load the driver
//        Class.forName("org.postgresql.Driver");
////        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//    } catch (ClassNotFoundException e) {
//        System.out.println("Unable to load Driver Class");
//        return test;
//    }
//    Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/postgres", "postgres", "12345");
//    Statement s = con.createStatement();
//    s.executeUpdate("");
//    s.executeUpdate("CREATE TABLE TestTable(test int);");
//    s.executeUpdate("INSERT INTO TestTable(test) VALUES (1);");
//    ResultSet rs = s.executeQuery("SELECT * from TestTable;");
//    while (rs.next()){
//        test = rs.getInt(1);
//    }
//    s.executeUpdate("DROP TABLE TestTable;");
//    con.close();
//    s.close();
//    rs.close();
//    return test;
//    }
//
//
//}
//
//
