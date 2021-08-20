package lection2;

import lection2.ext.ProductExt1;
import lection2.ext.ProductExt2;
import lection2.ext.ProductExt3;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateManager {
    private static final String dbURL = "jdbc:ucanaccess://C:\\Users\\Admin\\Documents\\Test.accdb";
    private final List<AbstractProductModel> models = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final Map<Long, Company> companies = new HashMap<>();

    public static void main(String[] args) throws Exception {
        StateManager manager = new StateManager();

        manager.addModel(new Product.ProductModel());
        manager.addModel(new ProductExt1.ProductModel());
        manager.addModel(new ProductExt2.ProductModel());
        manager.addModel(new ProductExt3.ProductModel());
        manager.load();

        for (var p : manager.getProducts(ProductExt1.class)) {
            System.out.println(p);
        }
    }

    static {
        try {
            // This is where we load the driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load Driver Class");
        }
    }

    protected void load() throws SQLException {
        products.clear();
        companies.clear();

        Connection con = DriverManager.getConnection(dbURL, "", "");
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getExtSQL());
            while (rs.next()) {
//                  var p = createProductByDiscriminator(rs);
                var p = createProductById(rs);
                products.add(p);
            }
            rs.close();
            stmt.close();

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Company");
            while (rs.next()) {
                var i = new Company(rs);
                companies.put(i.id, i);
            }
            rs.close();
            stmt.close();
        } finally {
            con.close();
        }

        for (var i : products) {
            i.company = companies.get(i.companyId);
            i.company.products.add(i);
        }
    }

    private String getExtSQL() {
        StringBuilder sql = new StringBuilder(1024);
        sql.append("SELECT ");
        var first = true;
        for (var m : models) {
            if (!first)
                sql.append(",");
            sql.append(m.columnNames);

            first = false;
        }

        sql.append(" FROM ");
        var p = models.get(0);
        sql.append("(".repeat(models.size() - 2));
        sql.append(p.tableName).append(" as ").append(p.alias);
        for (int i = 1; i < models.size(); i++) {
            p = models.get(i);
            sql.append(" LEFT JOIN ").append(p.tableName).append(" as ").append(p.alias).append(" ON p.id = ")
                    .append(p.alias).append(".id ");
            if (i+1 != models.size())sql.append(")");
        }
        return sql.toString();
    }

    private Product createProductById(ResultSet rs) throws SQLException {
        for (int i = models.size() - 1; i >= 0; i--) {
            var m = models.get(i);

            var p = m.create(rs);
            if (p.id > 0) {
                return p;
            }
        }
        return null;
    }

    public void addModel(AbstractProductModel model) {
        models.add(model);
    }

    public Product[] getProducts() throws SQLException {
        return products.toArray(new Product[0]);
    }

    public Company[] getCompanies() throws SQLException {
        return companies.values().toArray(new Company[0]);
    }

    private Product createProductByDiscriminator(ResultSet rs) throws SQLException {
        var disc = rs.getString("discriminator");

        var discModel = models.stream().filter(m -> m.discriminator.equals(disc)).findFirst().orElseThrow();
        return discModel.create(rs);
    }

    public <T extends Product> Product[] getProducts(Class<T> category) throws SQLException {
        return products.stream().filter(x -> category.isInstance(x)).toArray(Product[]::new);
    }
}
