package org.mama.funckydude;

import javax.swing.table.AbstractTableModel;

public class TraderTableModel extends AbstractTableModel {
    Trader m;

    public TraderTableModel(Trader t) {
        this.m = t;
        for (int i = 1; i < m.getGoods().length; i++) {
            getValueAt(i, 0);
        }
    }

    @Override
    public int getRowCount() {
        return m.getGoods().length;
    }

    @Override
    public int getColumnCount() {
        return Good.class.getFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Good g = m.getGoods()[rowIndex];
        switch (columnIndex) {
            case 0: {
                return g.getId();
            }
            case 1: {
                return g.getName();
            }
            case 2: {
                return g.getPrice();
            }
            case 3: {
                return g.trader.getName();
            }
        }
        return null;
    }
}
