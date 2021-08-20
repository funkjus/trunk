package lection2.ext;

import lection2.AbstractProductModel;
import lection2.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExt3 extends Product {
    public static class ProductModel extends AbstractProductModel {
        public ProductModel() {
            discriminator = "3";
            alias = "x3";
            tableName = "ProductExt3";
            columnNames = "x3.id, x3.param1, x3.param2";
        }

        public Product create(ResultSet rs) throws SQLException {
            return new ProductExt3(rs);
        }
    }

    public String param1, param2;

    public ProductExt3(ResultSet rs) throws SQLException {
        super(rs);
        id = rs.getLong(13);
        param1 = rs.getString(14);
        param2 = rs.getString(15);
    }
}
