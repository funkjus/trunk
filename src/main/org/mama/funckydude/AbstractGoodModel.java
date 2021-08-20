package org.mama.funckydude;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractGoodModel {
    public String alias, tableName, columnName;

    public abstract Good create(ResultSet rs) throws SQLException;
}
