package org.mama.secondProgect.SwingForms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.mama.secondProgect.DAO.Controls.GoodControl;
import org.mama.secondProgect.DAO.Controls.TraderControl;
import org.mama.secondProgect.DAO.Entity.Good;
import org.mama.secondProgect.DAO.Entity.GoodForWoman;
import org.mama.secondProgect.DAO.Entity.Trader;
import org.mama.secondProgect.View.GoodsTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;

public class GoodManipulationForm extends JDialog {
    private JPanel mainPanel;
    private JTextField nameGoodField;
    private JTextField priceGoodField;
    private JTextField colorGoodField;
    private JTextField sizeGoodField;
    private JLabel nameGood;
    private JLabel priceGood;
    private JLabel colorGood;
    private JLabel sizeGood;
    private JButton addGoodButton;

    public GoodManipulationForm(TraderForm traderForm, Trader t, TraderControl traderControl, GoodControl goodControl, JTable tableGoods) {
        super(traderForm, "Add good", ModalityType.DOCUMENT_MODAL);
        setContentPane(mainPanel);
        setBounds(200, 500, 250, 250);

        addGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameGoodField.getText().equals("") && !priceGoodField.getText().equals("")) {
                    long trader_id = t.id;
                    try {
                        double price = Double.parseDouble(priceGoodField.getText());
                        nameGood.setForeground(Color.DARK_GRAY);
                        priceGood.setForeground(Color.DARK_GRAY);
                        if (trader_id != 0) {
                            Timestamp date = new Timestamp(new Date().getTime()); // creating unique key for good with using Timestamp convert to string
                            String sDate = date.toString();
                            Good g = new Good(nameGoodField.getText(), price, t, date, sDate);
                            if (!colorGoodField.getText().equals("") || !sizeGoodField.getText().equals("")) {
                                GoodForWoman w = new GoodForWoman(nameGoodField.getText(), price, colorGoodField.getText(), sizeGoodField.getText(), t, date, sDate);
                                goodControl.insert(w);
                            } else goodControl.insert(g);
                            tableGoods.setModel(new GoodsTable(t));
                        }
                    } catch (NumberFormatException n) {
                        priceGood.setForeground(Color.RED);
                    }
                }
                if (nameGoodField.getText().equals("")) {
                    nameGood.setForeground(Color.RED);
                }
                if (priceGoodField.getText().equals("")) {
                    priceGood.setForeground(Color.RED);
                }
            }
        });
    }

    public GoodManipulationForm(TraderForm traderForm, long id_goods, Trader t, GoodControl goodControl, JTable tableGoods) {
        super(traderForm, "Add good", ModalityType.DOCUMENT_MODAL);
        setContentPane(mainPanel);
        addGoodButton.setText("Update");
        setBounds(200, 500, 250, 250);
        nameGoodField.setText((String) tableGoods.getValueAt(tableGoods.getSelectedRow(), 1));
        Double d = (Double) tableGoods.getValueAt(tableGoods.getSelectedRow(), 2);
        priceGoodField.setText(d.toString());
        colorGoodField.setText((String) tableGoods.getValueAt(tableGoods.getSelectedRow(), 4));
        sizeGoodField.setText((String) tableGoods.getValueAt(tableGoods.getSelectedRow(), 5));

        addGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameGoodField.getText().equals("") && !priceGoodField.getText().equals("")) {
                    long trader_id = t.id;
                    try {
                        double price = Double.parseDouble(priceGoodField.getText());
                        nameGood.setForeground(Color.DARK_GRAY);
                        priceGood.setForeground(Color.DARK_GRAY);
                        if (trader_id != 0) {
                            Timestamp date = new Timestamp(new Date().getTime()); // creating unique key for good with using Timestamp convert to string
                            String sDate = date.toString();
                            Good g = new Good(nameGoodField.getText(), price, t, date, sDate);
                            g.id = id_goods;
                            if (!colorGoodField.getText().equals("") || !sizeGoodField.getText().equals("")) {
                                GoodForWoman w = new GoodForWoman(nameGoodField.getText(), price, colorGoodField.getText(), sizeGoodField.getText(), t, date, sDate);
                                w.id = id_goods;
                                goodControl.update(w);
                            } else goodControl.update(g);
                            tableGoods.setModel(new GoodsTable(t));
                        }
                    } catch (NumberFormatException n) {
                        priceGood.setForeground(Color.RED);
                    }
                }
                if (nameGoodField.getText().equals("")) {
                    nameGood.setForeground(Color.RED);
                }
                if (priceGoodField.getText().equals("")) {
                    priceGood.setForeground(Color.RED);
                }
            }
        });

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setInheritsPopupMenu(false);
        mainPanel.setMaximumSize(new Dimension(216, 174));
        nameGood = new JLabel();
        nameGood.setText("Name");
        mainPanel.add(nameGood, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        priceGood = new JLabel();
        priceGood.setText("Price");
        mainPanel.add(priceGood, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        colorGood = new JLabel();
        colorGood.setText("Color");
        mainPanel.add(colorGood, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sizeGood = new JLabel();
        sizeGood.setText("Size");
        mainPanel.add(sizeGood, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameGoodField = new JTextField();
        mainPanel.add(nameGoodField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(96, 30), null, 0, false));
        priceGoodField = new JTextField();
        mainPanel.add(priceGoodField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(96, 30), null, 0, false));
        colorGoodField = new JTextField();
        mainPanel.add(colorGoodField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(96, 30), null, 0, false));
        sizeGoodField = new JTextField();
        mainPanel.add(sizeGoodField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(96, 30), null, 0, false));
        addGoodButton = new JButton();
        addGoodButton.setText("Add");
        mainPanel.add(addGoodButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(96, 30), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
