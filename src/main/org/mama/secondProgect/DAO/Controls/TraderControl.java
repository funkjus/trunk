package org.mama.secondProgect.DAO.Controls;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mama.GoldenThought.HibernateUtil;
import org.mama.secondProgect.DAO.Entity.Trader;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TraderControl {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Trader getTrader(long id) {
        Session session = sessionFactory.openSession();
        Trader trader = session.get(Trader.class,id);
        session.close();
        return trader;
    }

    public ArrayList<Trader> getArrayTraders() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Trader.class);
        Root<Trader> root = cq.from(Trader.class);
        cq.select(root);
        Query query = session.createQuery(cq);

        List<Trader> traderList = query.getResultList();
        session.close();

        return new ArrayList<>(traderList);

    }

    public void update(Trader t){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Trader trader = session.get(Trader.class,t.id);
        trader.name = t.name;
        trader.adress = t.adress;
        trader.url = t.url;
        session.save(trader);
        session.getTransaction().commit();
        session.close();
    }

    public void insert(Trader t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Trader trader = session.get(Trader.class,id);
        session.delete(trader);
        session.getTransaction().commit();
        session.close();
    }
}
