package org.mama.funckydude;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TraderEurope extends Trader {
    private long id;
    private String country;

    public TraderEurope() {
        alias = "teur";
        columnName = "teur.id, teur.country";
        tableName = "traders_europe";
    }

    public long getId() {
        return id;
    }

    public TraderEurope(ResultSet rs) throws SQLException {
        super(rs);

        this.id = rs.getLong(5);
        this.country = rs.getString(6);
    }

    public Trader create(ResultSet rs) throws SQLException {
        return new TraderEurope(rs);
    }

}
