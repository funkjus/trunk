package org.mama.GoldenThought.DAO.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "dictionary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(of = "id")
@ToString(of = "name", includeFieldNames = false)
public class Dictionary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    private String theme, language, area, source;

//    @Column(name = "second_name")
    private String secondName;

    public Dictionary(long id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(targetEntity = Word.class, mappedBy = "dictionary", fetch = FetchType.LAZY)
    private List<Word> words = new ArrayList<>();
}
