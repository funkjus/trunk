//package org.mama.postFuck;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.*;
//import java.util.Date;
//
//public class DbOperationTest {
//
//    @Before
//    public void init() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/postgres", "postgres", "12345");
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
//        connection.close();
//        statement.close();
//
//    }
//
//    @After
//    public void close() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/postgres", "postgres", "12345");
//        Statement statement = connection.createStatement();
//        statement.executeUpdate("DROP TABLE traders_europe;");
//        statement.executeUpdate("DROP TABLE goods_woman;");
//        statement.executeUpdate("DROP TABLE goods;");
//        statement.executeUpdate("DROP TABLE traders CASCADE;");
//        connection.close();
//        statement.close();
//    }
//
//    @Test
//    public void testExecuteTrader() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/postgres", "postgres", "12345");
//        Statement statement = connection.createStatement();
//        Trader t = new Trader("apple", "www.apple.com", "USA");
//        DbOperation.executeTrader(t);
//        ResultSet rs = statement.executeQuery("SELECT * FROM traders;");
//        String traderName = "";
//        while (rs.next()) {
//            traderName = rs.getString("name");
//        }
//        rs.close();
//        Assert.assertEquals(traderName, "apple");
//        connection.close();
//        statement.close();
//    }
//
//    @Test
//    public void testExecuteGoodForWoman() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/postgres", "postgres", "12345");
//        Statement statement = connection.createStatement();
//        Trader t = new Trader("apple", "www.apple.com", "USA");
//        DbOperation.executeTrader(t);
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM traders;");
//        long traderId = 0;
//        while (resultSet.next()) {
//            traderId = resultSet.getLong("id");
//        }
//        resultSet.close();
//        Timestamp date = new Timestamp(new Date().getTime());
//        String sDate = date.toString();
//        GoodForWoman g = new GoodForWoman("iphone", 200.1, "pink", "large", traderId, date, sDate);
//        DbOperation.insertGood(g);
//        ResultSet rg = statement.executeQuery("SELECT * FROM goods;");
//        String goodName = "";
//        long goodId = 0;
//        while (rg.next()) {
//            goodName = rg.getString("name");
//            goodId = rg.getLong("id");
//        }
//        rg.close();
//        Assert.assertEquals(goodName, "iphone");
//        ResultSet rgWoman = statement.executeQuery("SELECT * FROM goods_woman WHERE id = " + goodId + ";");
//        String goodWomanGoodColor = "";
//        while (rgWoman.next()) {
//            goodWomanGoodColor = rgWoman.getString("color");
//        }
//        rgWoman.close();
//        Assert.assertEquals(goodWomanGoodColor, "pink");
//        connection.close();
//        statement.close();
//    }
//
//    @Test
//    public void testExecuteGoodNotWoman() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/postgres", "postgres", "12345");
//        Statement statement = connection.createStatement();
//        Trader t = new Trader("apple", "www.apple.com", "USA");
//        DbOperation.executeTrader(t);
//        ResultSet rs = statement.executeQuery("SELECT * FROM traders;");
//        long traderId = 0;
//        while (rs.next()) {
//            traderId = rs.getLong("id");
//        }
//        rs.close();
//        Timestamp date = new Timestamp(new Date().getTime());
//        String sDate = date.toString();
//        GoodForWoman g = new GoodForWoman("iphone", 200.1, "", "", traderId, date, sDate);
//        DbOperation.insertGood(g);
//        ResultSet rg = statement.executeQuery("SELECT * FROM goods;");
//        String goodName = "";
//        while (rg.next()) {
//            goodName = rg.getString("name");
//        }
//        rg.close();
//        Assert.assertEquals(goodName, "iphone");
//        ResultSet rgWoman = statement.executeQuery("SELECT count(*) FROM goods_woman ;");
//        int count = 1;
//        while (rgWoman.next()) {
//            count = rgWoman.getInt("count");
//        }
//        rgWoman.close();
//        Assert.assertEquals(count, 0);
//        connection.close();
//        statement.close();
//    }
//
//    @Test
//    public void testDelGoods() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/postgres", "postgres", "12345");
//        Statement statement = connection.createStatement();
//        Trader t = new Trader("apple", "www.apple.com", "USA");
//        DbOperation.executeTrader(t);
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM traders;");
//        long traderId = 0;
//        while (resultSet.next()) {
//            traderId = resultSet.getLong("id");
//        }
//        resultSet.close();
//        Timestamp date = new Timestamp(new Date().getTime());
//        String sDate = date.toString();
//        GoodForWoman g = new GoodForWoman("iphone", 200.1, "pink", "large", traderId, date, sDate);
//        DbOperation.insertGood(g);
//        ResultSet rg = statement.executeQuery("SELECT * FROM goods;");
//        long goodId = 0;
//        while (rg.next()) {
//            goodId = rg.getLong("id");
//        }
//        rg.close();
//        DbOperation.delGoods(goodId);
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