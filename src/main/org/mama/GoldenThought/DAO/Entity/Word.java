package org.mama.GoldenThought.DAO.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "word")
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
public class Word implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name, transcription, pronunciation, translation;

    @ManyToOne
//    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;
}
