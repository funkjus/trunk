package lection2.ext;

import lection2.AbstractProductModel;
import lection2.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExt2 extends Product {
    public static class ProductModel extends AbstractProductModel {
        public ProductModel() {
            discriminator = "2";
            alias = "x2";
            tableName = "ProductExt2";
            columnNames = "x2.id, x2.param1, x2.param2";
        }

        public Product create(ResultSet rs) throws SQLException {
            return new ProductExt2(rs);
        }
    }

    public String param1, param2;

    public ProductExt2(ResultSet rs) throws SQLException {
        super(rs);

        id = rs.getLong(10);
        param1 = rs.getString(11);
        param2 = rs.getString(12);
    }

    public String toString() {
        return super.toString() + ";" + param1 + ";" + param2;
    }
}
