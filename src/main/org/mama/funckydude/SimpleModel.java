package org.mama.funckydude;

import javax.swing.table.AbstractTableModel;

public class SimpleModel extends AbstractTableModel {
    MyStateManager m;

    public SimpleModel(MyStateManager myStateManager) {
        this.m = myStateManager;
        for (int i = 1; i < m.getProducts().length; i++) {
            getValueAt(i, 0);
        }
    }

    @Override
    public int getRowCount() {
        return m.getProducts().length;
    }

    @Override
    public int getColumnCount() {
        return Good.class.getFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Good g = m.getProducts()[rowIndex];
        switch (columnIndex) {
            case 0: {
                return g.getName();
            }
            case 1: {
                return g.getId();
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
