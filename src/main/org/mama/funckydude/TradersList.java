package org.mama.funckydude;

import javax.swing.*;
import javax.swing.event.ListDataListener;

public class TradersList implements ListModel {
    MyStateManager m;
    String[] a;

    public TradersList(MyStateManager m) {
        this.m = m;

    }

    @Override
    public int getSize() {
        return m.getTraders().length;
    }

    @Override
    public Object getElementAt(int index) {
        return m.getTraders()[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
