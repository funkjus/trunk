//package org.mama.secondProgect;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mama.secondProgect.DAO.Entity.GoodForWoman;
//import org.mama.secondProgect.DAO.Controls.GoodControl;
//import org.mama.secondProgect.DAO.Controls.TraderControl;
//
//import java.sql.Timestamp;
//import java.util.Date;
//
//import java.sql.*;
//
//public class GoodControlTest {
//    @Before
//    public void init() throws SQLException {
//        Connection connection = DriverManager.getConnection(Starter.DB_URL, "postgres", "12345");
//        Statement statement = connection.createStatement();
//        statement.executeUpdate("CREATE TABLE \"public\".\"traders\" (\n" +
//                "  \"id\" int4 NOT NULL DEFAULT nextval('traders_id_seq'::regclass),\n" +
//                "  \"url\" varchar(50) COLLATE \"pg_catalog\".\"default\",\n" +
//                "  \"name\" varchar(255) COLLATE \"pg_catalog\".\"default\",\n" +
//                "  \"adress\" text COLLATE \"pg_catalog\".\"default\",\n" +
//                "  CONSTRAINT \"Traders_pkey\" PRIMARY KEY (\"id\")\n" +
//                ")\n" +
//                ";");
//        statement.executeUpdate("ALTER TABLE \"public\".\"traders\" \n" +
//                "  OWNER TO \"postgres\";");
//        statement.executeUpdate("CREATE TABLE \"public\".\"goods\" (\n" +
//                "  \"id\" int4 NOT NULL DEFAULT nextval('traders_id_seq'::regclass),\n" +
//                "  \"traders_id\" int4 NOT NULL,\n" +
//                "  \"name\" varchar(255) COLLATE \"pg_catalog\".\"default\",\n" +
//                "  \"price\" float8,\n" +
//                "  \"date\" timestamptz(6),\n" +
//                "  \"s_date\" varchar(255) COLLATE \"pg_catalog\".\"default\",\n" +
//                "  CONSTRAINT \"Goods_pkey\" PRIMARY KEY (\"id\"),\n" +
//                "  CONSTRAINT \"2\" FOREIGN KEY (\"traders_id\") REFERENCES \"public\".\"traders\" (\"id\") ON DELETE CASCADE ON UPDATE CASCADE\n" +
//                ")\n" +
//                ";");
//        statement.executeUpdate("\n" +
//                "ALTER TABLE \"public\".\"goods\" \n" +
//                "  OWNER TO \"postgres\";");
//        statement.executeUpdate("CREATE TABLE \"public\".\"goods_woman\" (\n" +
//                "  \"id\" int4,\n" +
//                "  \"color\" varchar(255) COLLATE \"pg_catalog\".\"default\",\n" +
//                "  \"size\" varchar(255) COLLATE \"pg_catalog\".\"default\",\n" +
//                "  CONSTRAINT \"2\" FOREIGN KEY (\"id\") REFERENCES \"public\".\"goods\" (\"id\") ON DELETE CASCADE ON UPDATE CASCADE\n" +
//                ")\n" +
//                ";");
//        statement.executeUpdate("ALTER TABLE \"public\".\"goods_woman\" \n" +
//                "  OWNER TO \"postgres\";");
//        statement.executeUpdate("CREATE TABLE \"public\".\"traders_europe\" (\n" +
//                "  \"id\" int4,\n" +
//                "  \"country\" varchar(255) COLLATE \"pg_catalog\".\"default\",\n" +
//                "  CONSTRAINT \"1\" FOREIGN KEY (\"id\") REFERENCES \"public\".\"traders\" (\"id\") ON DELETE CASCADE ON UPDATE CASCADE\n" +
//                ")\n" +
//                ";");
//        statement.executeUpdate("ALTER TABLE \"public\".\"traders_europe\" \n" +
//                "  OWNER TO \"postgres\";");
//
//        connection.close();
//        statement.close();
//
//    }
//
////    @After
////    public void close() throws SQLException {
////        Connection connection = DriverManager.getConnection(Starter.DB_URL, "postgres", "12345");
////        Statement statement = connection.createStatement();
////        statement.executeUpdate("DROP TABLE traders_europe;");
////        statement.executeUpdate("DROP TABLE goods_woman;");
////        statement.executeUpdate("DROP TABLE goods;");
////        statement.executeUpdate("DROP TABLE traders CASCADE;");
////        connection.close();
////        statement.close();
////    }
//
//
//    @Test
//    public void testDelete() throws SQLException {
//        GoodControl goodControl = new GoodControl();
//        TraderControl traderControl = new TraderControl();
//        Connection connection = DriverManager.getConnection(Starter.DB_URL, "postgres", "12345");
//        Statement statement = connection.createStatement();
//        org.mama.secondProgect.DAO.Entity.Trader t = new org.mama.secondProgect.DAO.Entity.Trader("gapa", "www.apple.com", "USA");
//        traderControl.insert(t);
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM traders;");
//        long traderId = 0;
//        while (resultSet.next()) {
//            traderId = resultSet.getLong("id");
//        }
//        resultSet.close();
//        Timestamp date = new Timestamp(new Date().getTime());
//        String sDate = date.toString();
//        GoodForWoman g = new GoodForWoman("iphone", 200.1, "pink", "large", t, date, sDate);
//        goodControl.insert(g);
//        ResultSet rg = statement.executeQuery("SELECT * FROM goods;");
//        long goodId = 0;
//        while (rg.next()) {
//            goodId = rg.getLong("id");
//        }
//        rg.close();
//        goodControl.delete(goodId);
//        ResultSet rgVoid = statement.executeQuery("SELECT count(*) FROM goods;");
//        int count = 1;
//        while (rgVoid.next()) {
//            count = rgVoid.getInt("count");
//        }
//        rgVoid.close();
//        Assert.assertEquals(count, 0);
//        connection.close();
//        statement.close();
//    }
//}
