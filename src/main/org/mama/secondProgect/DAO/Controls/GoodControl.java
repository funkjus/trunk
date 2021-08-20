package org.mama.secondProgect.DAO.Controls;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mama.secondProgect.DAO.Entity.Good;
import org.mama.secondProgect.DAO.Entity.GoodForWoman;
import org.mama.secondProgect.DAO.Entity.Trader;
import org.mama.secondProgect.HibernateUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GoodControl {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Good getGood(long id) {
        Session session = sessionFactory.openSession();
        Good good = session.get(Good.class, id);
        session.close();
        return good;
    }

    public Good[] getAllGoods() {
        return null;
    }

    public Good[] getTraderGoods(long id) {
        Session session = sessionFactory.openSession();
        Trader trader = session.get(Trader.class, id);
        List<Good> goodList = trader.goodList;
        session.close();
        return goodList.toArray(new Good[0]);
    }

    public GoodForWoman[] getGoodsForWoman(long id) {
        Session session = sessionFactory.openSession();
        Trader trader = session.get(Trader.class, id);
        List<Good> goodList = trader.goodList;
        List<GoodForWoman> goodForWomen = new ArrayList<>();
        for (Good g : goodList) {
            if (g instanceof GoodForWoman) {
                goodForWomen.add((GoodForWoman) g);
            }
        }
        session.close();
        return goodForWomen.toArray(new GoodForWoman[0]);
    }

    public void insert(Good g) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(g);
        session.getTransaction().commit();
        session.close();
    }

    public void insert(GoodForWoman g) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(g);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Good g) {
        Session session = sessionFactory.openSession();
        Good good = session.get(Good.class, g.id);
        good.name = g.name;
        good.price = g.price;
        session.beginTransaction();
        session.save(good);
        session.getTransaction().commit();
        session.close();
    }

    public void update(GoodForWoman g) {
        Session session = sessionFactory.openSession();
        GoodForWoman good = session.get(GoodForWoman.class, g.id);
        good.name = g.name;
        good.price = g.price;
        good.color = g.color;
        good.size = g.size;
        session.beginTransaction();
        session.save(good);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Good good = session.get(Good.class, id);
        session.delete(good);
        session.getTransaction().commit();
        session.close();
    }

}
