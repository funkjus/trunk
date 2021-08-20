package org.mama.GoldenThought;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mama.GoldenThought.DAO.Entity.Dictionary;
import org.mama.GoldenThought.DAO.Entity.Dictionary_;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class DictionaryHelper {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Dictionary> getAllDictionary() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Dictionary.class);
        Root<Dictionary> root = cq.from(Dictionary.class);
        cq.select(root);

        Query query = session.createQuery(cq);

        List<Dictionary> dictionaryList = query.getResultList();
        session.close();
        return dictionaryList;
    }

    public List<Dictionary> getAllDictionaryName() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Dictionary.class);
        Root<Dictionary> root = cq.from(Dictionary.class);
        Selection[] selections={root.get(Dictionary_.id),root.get(Dictionary_.name)};

        ParameterExpression<String> nameParam = cb.parameter(String.class,"name");

        cq.select(cb.construct(Dictionary.class,selections)).where(cb.like(root.get(Dictionary_.name), nameParam));

        Query query = session.createQuery(cq);
        query.setParameter("name", "%быть%");

        List<Dictionary> dictionaryList = query.getResultList();
        session.close();
        return dictionaryList;
    }

    public Dictionary addDictionary(Dictionary dictionary) {
        Session session = sessionFactory.openSession();

//        Dictionary d1 = session.get(Dictionary.class, 2L);
//        d1.setName("Java Interview Questions and Answers (2021)");

        session.beginTransaction();

        session.save(dictionary);

        session.getTransaction().commit();

        session.close();

        return dictionary;
    }

    public void addFifteen() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int i = 0;
        for (i = 1; i <= 50; i++) {
            Dictionary dictionary = new Dictionary("name" +i);
            session.save(dictionary);
            if (i % 10 == 0) {
                session.flush();
            }
        }
        session.getTransaction().commit();
        session.close();
    }
    public Dictionary getDictionary(long id){
        Session session = sessionFactory.openSession();
        Dictionary dictionary = session.get(Dictionary.class,id);
        dictionary.getWords().get(0).getName();
        return dictionary;
    }

}
