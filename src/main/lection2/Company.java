package lection2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Company {
    public long id;
    public String url, name;
    public double capacity;
    public List<Product> products = new ArrayList<>();

    public Company(ResultSet rs) throws SQLException {
        id = rs.getLong("id");
        name = rs.getString("name");
        url = rs.getString("url");
        capacity = rs.getDouble("capacity");
    }
}
