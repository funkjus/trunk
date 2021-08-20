package lection2;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    public static class ProductModel extends AbstractProductModel {
        public ProductModel() {
            discriminator = "0";
            alias = "p";
            tableName = "Product";
            columnNames = "p.id, p.company, p.name, p.price, p.amount, p.discriminator";
        }

        public Product create(ResultSet rs) throws SQLException {
            return new Product(rs);
        }
    }

    public long id, companyId;
    public Company company;
    public String name, discriminator;
    public double price, amount;

    public Product(ResultSet rs) throws SQLException {
        id = rs.getLong(1);
        companyId = rs.getLong(2);
        name = rs.getString(3);
        price = rs.getDouble(4);
        amount = rs.getDouble(5);
        discriminator = rs.getString(6);
    }

    public String toString() {
        return id + ";" + name;
    }
}
