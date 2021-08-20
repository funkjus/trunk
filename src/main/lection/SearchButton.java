package lection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchButton implements ActionListener {
    JTable table;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            table.setModel(JdbcAccess.getModel("Goods", null));
        } catch (Exception ex) {
        }
    }

    public SearchButton(JTable table){
        this.table = table;
    }
}
