package org.mama.secondProgect.service;

import org.mama.secondProgect.DAO.Controls.TraderControl;
import org.mama.secondProgect.DAO.Entity.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderService {

    @Autowired
    private TraderControl traderControl;

    public List<Trader> allTraders() {
        return traderControl.getArrayTraders();
    }

    public Trader getTrader(int id) {
        return traderControl.getTrader(id);
    }

    public void updateTrader(Trader t) {
        traderControl.update(t);
    }

    public void addTrader(Trader t) {
        traderControl.insert(t);
    }

    public void delTrader(Trader t){
        traderControl.delete(t.id);
    }

}
