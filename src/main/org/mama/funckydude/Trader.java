package org.mama.funckydude;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trader extends AbstractTraderModel {
    private long id;
    private String url;
    private String name;
    private String adress;

    public List<Good> gds = new ArrayList<>();
    public HashMap<Long, Good> good = new HashMap<>();

    public Trader() {
        alias = "t";
        tableName = "traders";
        columnName = "t.id, t.url, t.name, t.adress";
    }

    public Trader(String name, String url, String adress) {
        this.name = name;
        this.url = url;
        this.adress = adress;
    }

    public Trader(ResultSet rs) throws SQLException {
        this.id = rs.getLong(1);
        this.url = rs.getString(2);
        this.name = rs.getString(3);
        this.adress = rs.getString(4);
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Good[] getGoods() {
        return good.values().toArray(new Good[0]);
    }


    public String getAdress() {
        return adress;
    }

    public String toString() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public Trader create(ResultSet rs) throws SQLException {
        return new Trader(rs);
    }

}
