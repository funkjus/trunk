package lection2.ext;

import lection2.AbstractProductModel;
import lection2.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExt1 extends Product {
    public static class ProductModel extends AbstractProductModel {
        public ProductModel() {
            discriminator = "1";
            alias = "x1";
            tableName = "ProductExt1";
            columnNames = "x1.id, x1.param1, x1.param2";
        }

        public Product create(ResultSet rs) throws SQLException {
            return new ProductExt1(rs);
        }
    }

    public String param1, param2;

    public ProductExt1(ResultSet rs) throws SQLException {
        super(rs);

        id = rs.getLong(7);
        param1 = rs.getString(8);
        param2 = rs.getString(9);
    }

    public String toString() {
        return super.toString() + ";" + param1 + ";" + param2;
    }
}
