package org.mama.funckydude;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Good extends AbstractGoodModel {
    private long id;
    private long trID;
    private String name;
    private double price;
    public Trader trader;
    public Timestamp update;
    public String sDate;

    public Good() {
        alias = "g";
        tableName = "goods";
        columnName = "g.id, g.traders_id, g.name, g.price, g.date, g.s_date";
    }

    public Good(String name, double price, long trader_id, Timestamp date, String sDate) {
        this.name = name;
        this.price = price;
        this.trID = trader_id;
        this.update = date;
        this.sDate = sDate;
    }

    public Good(ResultSet rs) throws SQLException {
        this.id = rs.getLong(1);
        this.trID = rs.getLong(2);
        this.name = rs.getString(3);
        this.price = rs.getDouble(4);
        this.update = rs.getTimestamp(5);
        this.sDate = rs.getString(6);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getTraderId() {
        return this.trID;
    }

    public double getPrice() {
        return this.price;
    }

    public Timestamp getUpdate() {
        return this.update;
    }

    @Override
    public Good create(ResultSet rs) throws SQLException {
        return new Good(rs);
    }


}
