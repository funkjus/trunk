package org.mama.secondProgect.DAO.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "goods")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@DynamicUpdate //при посторении запросов обновляет только изменившиеся поля
@DynamicInsert
@Getter @Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "name", includeFieldNames = false)

public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

//    public long trID;

    public String name;

    public double price;

    @ManyToOne
    @JoinColumn(name = "traders_id")
    public Trader trader;
    @Column(name = "date")
    public Timestamp update;
    @Column(name = "s_date")
    public String sDate;

    public Good(String name, double price, Trader trader, Timestamp date, String sDate) {
        this.name = name;
        this.price = price;
        this.trader = trader;
        this.update = date;
        this.sDate = sDate;
    }

}
