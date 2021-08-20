package org.mama.secondProgect.service;

import org.mama.secondProgect.DAO.Controls.GoodControl;
import org.mama.secondProgect.DAO.Entity.Good;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GoodService {

    GoodControl goodControl;

    public List<Good> getTraderGoods(long id) {
        return Arrays.asList(goodControl.getTraderGoods(id).clone());
    }

    public Good getGood(long id){
        return goodControl.getGood(id);
    }

    public void update(Good g){
        goodControl.update(g);
    }

    public void add(Good g){
        goodControl.insert(g);
    }
    public void delete(long id){
        goodControl.delete(id);
    }
}
