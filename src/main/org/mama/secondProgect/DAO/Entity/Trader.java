package org.mama.secondProgect.DAO.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "traders")
@NoArgsConstructor
@Getter @Setter
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(of = "id")
public class Trader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String url;

    public String name;

    public String adress;

    @OneToMany(targetEntity = Good.class, mappedBy = "trader", fetch = FetchType.EAGER)
    public List<Good> goodList;

    public Trader(String name, String url, String adress) {
        this.name = name;
        this.url = url;
        this.adress = adress;
    }

    @Override
    public String toString(){
        return name;
    }
}
