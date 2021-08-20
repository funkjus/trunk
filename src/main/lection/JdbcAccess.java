package lection;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class JdbcAccess {

    public static void main(java.lang.String[] args) throws Exception {
        var model = getModel("Goods", "п");

        // Display the SQL Results
        for (var v : model.getDataVector()) {
            System.out.println(v.toString());
        }
    }

    public static DefaultTableModel getModel(String tableName, String namePattern) throws Exception {
        try {
            // This is where we load the driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load Driver Class");
            return null;
        }

        // All database access is within a try/catch block. Connect to database,
        // specifying particular database, username, and password
        String dbURL = "jdbc:ucanaccess://C:\\Users\\Admin\\Documents\\Trades.accdb";
        Connection con = DriverManager.getConnection(dbURL, "", "");

        // Create and execute an SQL Statement
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + (namePattern == null ? "" : " WHERE name LIKE '"+ namePattern + "%'"));
        var meta = rs.getMetaData();
        Vector<String> columns = new Vector<String>();
        for (int i = 1; i <= meta.getColumnCount(); i++)
            columns.add(meta.getColumnName(i));

        Vector<Vector> data = new Vector<Vector>();
        while (rs.next()) {
            Vector row = new Vector();
            for (int i = 1; i <= meta.getColumnCount(); i++)
                row.add(rs.getString(i));

            data.add(row);
        }

        // Make sure our database resources are released
        rs.close();
        stmt.close();
        con.close();

        return new DefaultTableModel(data, columns);
    }
}
