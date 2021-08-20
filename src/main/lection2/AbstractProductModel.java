package lection2;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractProductModel {
	public String alias, tableName, columnNames, discriminator;

	public abstract Product create(ResultSet rs) throws SQLException;
}
