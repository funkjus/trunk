package org.mama.secondProgect.DAO.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "goods_woman")
@NoArgsConstructor
@DynamicUpdate //при посторении запросов обновляет только изменившиеся поля
@DynamicInsert
@Getter @Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "name", includeFieldNames = false)
public class GoodForWoman extends Good {

    @Column(name = "color")
    public String color;
    @Column(name = "size")
    public String size;

    public GoodForWoman(String name, Double price, String color, String size, Trader t, Timestamp date, String sDate) {
        super(name, price, t, date, sDate);

        this.id = id;
        this.color = color;
        this.size = size;
    }

}
