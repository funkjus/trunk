//package org.mama.postFuck;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.*;
//
//public class TraderTableModelTest {
//    private static TraderTableModel traderTableModel;
//    private static Trader trader;
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
//        statement.executeUpdate("INSERT INTO traders (id,url,name,adress) VALUES (300,'www.apple.com','Apple','USA');");
//        statement.executeUpdate("INSERT INTO traders (id,url,name,adress) VALUES (301,'www.papaJons.com','Papa Jons','Germany');");
//        statement.executeUpdate("INSERT INTO traders (id,url,name,adress) VALUES (302,'www.vlas.by','Vlasevskii Boris','Belarus');");
//        statement.executeUpdate("INSERT INTO traders (id,url,name,adress) VALUES (303,'www.zara.com','Zara','Italy');");
//        statement.executeUpdate("INSERT INTO traders (id,url,name,adress) VALUES (304,'www.samsung.kr','Samsung','Koreya');");
//        statement.executeUpdate("INSERT INTO traders_europe (id,country) VALUES (300,'Germany');");
//        statement.executeUpdate("INSERT INTO traders_europe (id,country) VALUES (303,'Italy');");
//        statement.executeUpdate("INSERT INTO goods (id,traders_id,name,price) VALUES (250,300,'Iphone5g',600.50);");
//        statement.executeUpdate("INSERT INTO goods (id,traders_id,name,price) VALUES (251,300,'Iphone6',659.99);");
//        statement.executeUpdate("INSERT INTO goods_woman (id,color,size) VALUES (251,'pink','small');");
//        statement.executeUpdate("INSERT INTO goods (id,traders_id,name,price) VALUES (252,300,'Iphone6',799.99);");
//        statement.executeUpdate("INSERT INTO goods_woman (id,color,size) VALUES (252,'pink','large');");
//        statement.executeUpdate("INSERT INTO goods (id,traders_id,name,price) VALUES (253,300,'Iphone6',499.99);");
//        statement.executeUpdate("INSERT INTO goods (id,traders_id,name,price) VALUES (255,303,'Сумка',75.30);");
//        statement.executeUpdate("INSERT INTO goods (id,traders_id,name,price) VALUES (256,303,'Сумка',99.30);");
//        statement.executeUpdate("INSERT INTO goods_woman (id,color,size) VALUES (256,'red','small');");
//        ResultSet rs = statement.executeQuery("SELECT * FROM traders WHERE id = 300");
//        String tradersName = "";
//        while (rs.next()) {
//            trader = new Trader(rs);
//            tradersName = trader.toString();
//        }
//        rs.close();
//        Assert.assertEquals(tradersName, "Apple");
//        ResultSet rg = statement.executeQuery("SELECT * FROM goods WHERE traders_id = 300");
//        while (rg.next()) {
//            Good g = new Good(rg);
//            trader.good.put(g.getId(), g);
//            g.trader = trader;
//        }
//        rg.close();
//        traderTableModel = new TraderTableModel(trader);
//        connection.close();
//        statement.close();
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
//    public void testGetRowCount() {
//        Assert.assertEquals(trader.getGoods().length, traderTableModel.getRowCount());
//    }
//
//    @Test
//    public void testGetColumnCount() {
//        Assert.assertEquals(traderTableModel.getColumnCount(), 4);
//    }
//
//    @Test
//    public void testGetValueAt() {
//        int j;
//        StringBuilder concat = new StringBuilder();
//        for (j = 0; j < 4; j++) {
//            Object o = traderTableModel.getValueAt(0, j);
//            concat.append(o.toString()).append(" ");
//        }
//        Assert.assertEquals(concat.toString(), "250 Iphone5g 600.5 Apple ");
//    }
//
//
//}
