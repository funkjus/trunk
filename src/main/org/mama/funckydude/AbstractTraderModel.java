package org.mama.funckydude;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractTraderModel {
    public String alias, columnName, tableName;

    public abstract Trader create(ResultSet rs) throws SQLException;
}
