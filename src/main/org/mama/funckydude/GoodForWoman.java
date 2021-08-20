package org.mama.funckydude;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GoodForWoman extends Good {
    private long id;
    private String color;
    private String size;

    public GoodForWoman() {
        alias = "gfw";
        columnName = "gfw.id, gfw.color, gfw.size";
        tableName = "goods_woman";
    }

    public GoodForWoman(String name, Double price, String color, String size, long id, Timestamp date, String sDate) {
        super(name, price, id, date, sDate);

        this.id = id;
        this.color = color;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }


    public GoodForWoman(ResultSet rs) throws SQLException {
        super(rs);

        this.id = rs.getLong(7);
        this.color = rs.getString(8);
        this.size = rs.getString(9);
    }

    public Good create(ResultSet rs) throws SQLException {
        return new GoodForWoman(rs);
    }

}
