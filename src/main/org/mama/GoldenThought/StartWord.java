package org.mama.GoldenThought;

import org.mama.GoldenThought.DAO.Entity.Dictionary;

public class StartWord {

    public static void main(String[] args) {
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//
//        Dictionary d1 = session.get(Dictionary.class, 2L);
//        d1.setName("Java Interview Questions and Answers (2021)");
//        d1.getWords().get(2).getName();
//        System.out.println(d1.getWords().get(2).getName());
//        session.close();

//        Vector<Word> wordVector = new Vector<>(new WordHelper().getAllWords());
//        MainForm mainForm = new MainForm();

//        for (Word word: new WordHelper().getAllWords()){
//            System.out.println(word.getName());
//        }
        DictionaryHelper dictionaryHelper = new DictionaryHelper();
        Dictionary dictionary = dictionaryHelper.getDictionary(2);
        System.out.println(dictionary.getWords().get(0).getName());


//        for(Dictionary dictionary:dictionaryHelper.getAllDictionaryName()){
//            for(Word w:dictionary.getWords()){
//                System.out.println(w.getName());
//            }
//        }

//
//        WordHelper wordHelper = new WordHelper();
//        wordHelper.delete();
//        dictionaryHelper.addFifteen();


//        System.out.println(dictionarys.getName());
//
//        for (Dictionary dictionary: new DictionaryHelper().getAllDictionary()){
//            System.out.println(dictionary.getName());
//        }


    }
}
