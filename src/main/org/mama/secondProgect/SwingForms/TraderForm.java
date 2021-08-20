package org.mama.secondProgect.SwingForms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.mama.secondProgect.DAO.Controls.GoodControl;
import org.mama.secondProgect.DAO.Controls.TraderControl;
import org.mama.secondProgect.DAO.Entity.Trader;
import org.mama.secondProgect.View.GoodsTable;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class TraderForm extends JFrame {

    public GoodControl goodControl = new GoodControl();

    public TraderControl traderControl = new TraderControl();

    public ArrayList<Trader> traders;
    public int index;

    public void setAllOnForm(int index) {
        addGoodButton.setEnabled(false);
        dellTraderButton.setEnabled(false);
        allGoodsButton.setEnabled(false);
        womanGoodsButton.setEnabled(false);
        nextTrader.setEnabled(false);
        preTrader.setEnabled(false);
        nameTraderField.setText(null);
        urlTraderField.setText(null);
        tableGoods.setModel(new DefaultTableModel());
        if (traders.size() != 0) {
            addGoodButton.setEnabled(true);
            dellTraderButton.setEnabled(true);
            allGoodsButton.setEnabled(true);
            womanGoodsButton.setEnabled(true);
            nextTrader.setEnabled(true);
            preTrader.setEnabled(true);
            nameTraderField.setText(traders.get(index).toString());
            urlTraderField.setText(traders.get(index).url);
            tableGoods.setModel(new GoodsTable(traders.get(index)));
        }
    }

    private JPanel mainPanel;
    private JTable tableGoods;
    private JTextField nameTraderField;
    private JTextField urlTraderField;
    private JLabel TraderLabel;
    private JLabel nameTrader;
    private JLabel urlName;
    private JLabel goodsTableName;
    private JScrollPane scrollGoods;
    private JButton addTraderButton;
    private JButton dellTraderButton;
    private JButton addGoodButton;
    private JButton dellGoodButton;
    private JButton preTrader;
    private JButton nextTrader;
    private JButton womanGoodsButton;
    private JButton allGoodsButton;
    private JButton updateGoodButton;


    public TraderForm() {
        super("Traders");
        setContentPane(mainPanel);
        index = 0;
        updateGoodButton.setEnabled(false);
        dellGoodButton.setEnabled(false);

        traders = traderControl.getArrayTraders();
        if (index == 0) {
            setAllOnForm(index);
        }

        nextTrader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s;
                if (index < traders.size() - 1) {
                    index++;
                    setAllOnForm(index);
                }
            }
        });
        preTrader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s;
                if (index > 0) {
                    index--;
                    setAllOnForm(index);
                }
            }
        });

        addTraderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTraderForm addTraderForm = new AddTraderForm(traderControl, TraderForm.this);
                addTraderForm.setVisible(true);
            }
        });


        addGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoodManipulationForm goodManipulationForm = new GoodManipulationForm(TraderForm.this, traders.get(index), traderControl, goodControl, tableGoods);
                goodManipulationForm.setVisible(true);

            }
        });
        dellTraderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long trader_id = traders.get(index).id;
                traderControl.delete(trader_id);
                traders = traderControl.getArrayTraders();
                if (index >= traders.size()) {
                    index = traders.size() - 1;
                }
                setAllOnForm(index);
            }
        });
        dellGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long id = (long) tableGoods.getValueAt(tableGoods.getSelectedRow(), 0);
                goodControl.delete(id);
                tableGoods.setModel(new GoodsTable(traders.get(index)));
                updateGoodButton.setEnabled(false);
                dellGoodButton.setEnabled(false);
            }
        });

        tableGoods.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tableGoods.getSelectedRow() == -1) {
                    updateGoodButton.setEnabled(false);
                    dellGoodButton.setEnabled(false);
                }
                if (tableGoods.getSelectedRow() != -1) {
                    updateGoodButton.setEnabled(true);
                    dellGoodButton.setEnabled(true);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        updateGoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long id = (long) tableGoods.getValueAt(tableGoods.getSelectedRow(), 0);
                GoodManipulationForm goodManipulationForm = new GoodManipulationForm(TraderForm.this, id, traders.get(index), goodControl, tableGoods);
                goodManipulationForm.setVisible(true);
                updateGoodButton.setEnabled(false);
                dellGoodButton.setEnabled(false);
            }
        });
        womanGoodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableGoods.setModel(new GoodsTable(traders.get(index), null));
            }
        });
        allGoodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableGoods.setModel(new GoodsTable(traders.get(index)));
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
        mainPanel.setLayout(new GridLayoutManager(9, 5, new Insets(0, 0, 0, 0), -1, -1));
        TraderLabel = new JLabel();
        TraderLabel.setText("Trader");
        mainPanel.add(TraderLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTrader = new JLabel();
        nameTrader.setText("Name");
        mainPanel.add(nameTrader, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        urlName = new JLabel();
        urlName.setText("URL");
        mainPanel.add(urlName, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        goodsTableName = new JLabel();
        goodsTableName.setHorizontalTextPosition(11);
        goodsTableName.setText("Goods");
        goodsTableName.setVerticalAlignment(1);
        mainPanel.add(goodsTableName, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollGoods = new JScrollPane();
        mainPanel.add(scrollGoods, new GridConstraints(4, 1, 4, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableGoods = new JTable();
        scrollGoods.setViewportView(tableGoods);
        nameTraderField = new JTextField();
        mainPanel.add(nameTraderField, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        urlTraderField = new JTextField();
        mainPanel.add(urlTraderField, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addTraderButton = new JButton();
        addTraderButton.setText("Add Trader");
        mainPanel.add(addTraderButton, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dellTraderButton = new JButton();
        dellTraderButton.setText("Dell Trader");
        mainPanel.add(dellTraderButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addGoodButton = new JButton();
        addGoodButton.setHorizontalTextPosition(2);
        addGoodButton.setText("Add Good");
        mainPanel.add(addGoodButton, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dellGoodButton = new JButton();
        dellGoodButton.setText("Dell Good");
        mainPanel.add(dellGoodButton, new GridConstraints(8, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        womanGoodsButton = new JButton();
        womanGoodsButton.setText("Woman Goods");
        mainPanel.add(womanGoodsButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        allGoodsButton = new JButton();
        allGoodsButton.setText("All Goods");
        mainPanel.add(allGoodsButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateGoodButton = new JButton();
        updateGoodButton.setText("Update Good");
        mainPanel.add(updateGoodButton, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nextTrader = new JButton();
        nextTrader.setText(">");
        mainPanel.add(nextTrader, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        preTrader = new JButton();
        preTrader.setText("<");
        mainPanel.add(preTrader, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
