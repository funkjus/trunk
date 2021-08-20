package org.mama.GoldenThought;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mama.GoldenThought.DAO.Entity.Word;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class WordHelper {
    private final SessionFactory sessionFactory;

    public WordHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Word> getAllWords() {
        Session session = sessionFactory.openSession();

        System.out.println(session.get(Word.class, 677).getName());

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Word.class);
        Root<Word> root = cq.from(Word.class);
        cq.select(root);

        Query query = session.createQuery(cq);

        List<Word> wordList = query.getResultList();

        session.close();
        return wordList;
    }
    public void delete(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Word word = session.get(Word.class, 1442);
        session.delete(word);
        session.getTransaction().commit();
        session.close();

    }

}
