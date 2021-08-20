package org.mama.funckydude;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyStateManager {
    public static void main(String[] args) {
        try {
            // This is where we load the driver
//            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load Driver Class");
            return;
        }
        MyStateManager manager = new MyStateManager();

    }

    public static void delGoods(long id) {
        try {

//            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Admin\\Мои документы\\Trades.accdb", "", "");
            Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/traders","postgres","12345");
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM goods WHERE id = " + id + ";");
            statement.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void executeTrader(Trader t) {
        try {
//            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Admin\\Мои документы\\Trades.accdb", "", "");
            Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/traders","postgres","12345");
            Statement statement = con.createStatement();
            StringBuilder s = new StringBuilder();
            s.append("INSERT INTO traders (name, url, adress) VALUES ('").append(t.getName()).append("', '").append(t.getUrl()).append("', '").append(t.getAdress()).append("');");
            statement.execute(s.toString());

            statement.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void executeGood(GoodForWoman g) {
        try {
//            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Admin\\Мои документы\\Trades.accdb", "", "");
            Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/traders","postgres","12345");
            Statement statement = con.createStatement();
            StringBuilder s = new StringBuilder();
            s.append("INSERT INTO goods (name, price, traders_id, date, s_date) VALUES ('").append(g.getName()).append("', '").append(g.getPrice()).append("', '").append(g.getTraderId()).append("', '").append(g.getUpdate()).append("', '").append(g.sDate).append("');");
            statement.execute(s.toString());
            String sql = "SELECT goods.id FROM goods WHERE goods.s_date = '" + g.sDate + "';";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                long id = rs.getLong(1);
                if (!g.getSize().equals("") || !g.getColor().equals("")) {
                    StringBuilder sTwo = new StringBuilder();
                    sTwo.append("INSERT INTO goods_woman (id, color, size) VALUES ('").append(id).append("', '").append(g.getColor()).append("', '").append(g.getSize()).append("');");
                    statement.execute(sTwo.toString());
                }
            }

            rs.close();
            statement.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Map<Long, Trader> traders = new HashMap<>();
    private Map<Long, Good> goods = new HashMap<>();
    private List<Good> modelGoods = new ArrayList<>();
    private List<Trader> modelTrader = new ArrayList<>();

    public MyStateManager() {
        try {
//            String dbURL = "jdbc:ucanaccess://C:\\Users\\Admin\\Мои документы\\Trades.accdb";
//            Connection con = DriverManager.getConnection(dbURL, "", "");
            Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/traders","postgres","12345");
            Statement stmt = con.createStatement();

            modelGoods.add(new Good());
            modelGoods.add(new GoodForWoman());
            modelTrader.add(new Trader());
            modelTrader.add(new TraderEurope());

            ResultSet rg = stmt.executeQuery(getSQL3());
            while (rg.next()) {
                Good g = CreateGoodsById(rg);
                goods.put(g.getId(), g);
            }
            rg.close();
            ResultSet rt = stmt.executeQuery(getSQL4());
            while (rt.next()) {
                Trader t = CreateTradersById(rt);
                traders.put(t.getId(), t);
            }
            rt.close();
            stmt.close();
            con.close();

            for (Good g : goods.values()) {
                g.trader = traders.get(g.getTraderId());
                g.trader.gds.add(g);
                g.trader.good.put(g.getId(), g);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getSQL3() {
        StringBuilder s = new StringBuilder();
        s.append("SELECT ");
        var p = modelGoods.get(0);
        s.append(p.columnName);
        int i;
        for (i = 1; i < modelGoods.size(); i++) {
            if (i < modelGoods.size()) {
                var g = modelGoods.get(i);
                s.append(", ").append(g.columnName);
            }
        }
        s.append(" FROM ").append(p.tableName).append(" AS ").append(p.alias);
        for (i = 1; i < modelGoods.size(); i++) {
            if (i < modelGoods.size()) {
                var g = modelGoods.get(i);
                s.append(" LEFT JOIN ").append(g.tableName).append(" AS ").append(g.alias).append(" ON ").append(p.alias).append(".id = ").append(g.alias).append(".id");
            }
        }
        return s.toString();
    }

    public String getSQL4() {
        StringBuilder s = new StringBuilder();
        s.append("SELECT ");
        var p = modelTrader.get(0);
        s.append(p.columnName);
        int i;
        for (i = 1; i < modelTrader.size(); i++) {
            if (i < modelTrader.size()) {
                var g = modelTrader.get(i);
                s.append(", ").append(g.columnName);
            }
        }
        s.append(" FROM ").append(p.tableName).append(" AS ").append(p.alias);
        for (i = 1; i < modelTrader.size(); i++) {
            if (i < modelTrader.size()) {
                var g = modelTrader.get(i);
                s.append(" LEFT JOIN ").append(g.tableName).append(" AS ").append(g.alias).append(" ON ").append(p.alias).append(".id = ").append(g.alias).append(".id");
            }
        }
        return s.toString();
    }

    public Good CreateGoodsById(ResultSet rs) throws SQLException {
        for (int i = modelGoods.size() - 1; i >= 0; i--) {
            var m = modelGoods.get(i);
            var p = m.create(rs);
            if (p.getId() > 0)
                return p;
        }
        return null;
    }

    public Trader CreateTradersById(ResultSet rs) throws SQLException {
        for (int i = modelTrader.size() - 1; i >= 0; i--) {
            var m = modelTrader.get(i);
            var p = m.create(rs);
            if (p.getId() > 0)
                return p;
        }
        return null;
    }

    public Good[] getProducts() {
        return goods.values().toArray(new Good[0]);
    }

    public Trader[] getTraders() {
        return traders.values().toArray(new Trader[0]);
    }
}
