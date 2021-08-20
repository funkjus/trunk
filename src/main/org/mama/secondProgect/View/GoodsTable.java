package org.mama.secondProgect.View;

import org.mama.secondProgect.DAO.Controls.GoodControl;
import org.mama.secondProgect.DAO.Entity.Good;
import org.mama.secondProgect.DAO.Entity.GoodForWoman;
import org.mama.secondProgect.DAO.Entity.Trader;

import javax.swing.table.AbstractTableModel;

public class GoodsTable extends AbstractTableModel {
    GoodControl goodControl = new GoodControl();
    Trader t;
    Good[] goods;

    public GoodsTable() {
    }

    //Creating object with Array Goods from DB
    public GoodsTable(Trader t) {
        this.t = t;
        this.goods = goodControl.getTraderGoods(t.id);
    }

    public GoodsTable(Trader t, Good g) {
        this.t = t;
        this.goods = goodControl.getGoodsForWoman(t.id);
    }

    @Override
    public int getRowCount() {
        if (goods.length == 0) {
            return 0;
        }
        return goods.length;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Good g = goods[rowIndex];
        switch (columnIndex) {
            case 0: {
                return g.id;
            }
            case 1: {
                return g.name;
            }
            case 2: {
                return g.price;
            }
            case 3: {
                return t.name;
            }
        }
        if (g instanceof GoodForWoman) {
            switch (columnIndex) {
                case 4: {
                    return ((GoodForWoman) g).color;
                }
                case 5: {
                    return ((GoodForWoman) g).size;
                }
            }
        }
        return null;
    }

    String[] employee = {"ID", "Название", "Цена", "Компания", "Цвет", "Размер"};

    @Override
    public String getColumnName(int index) {
        return employee[index];
    }

}
