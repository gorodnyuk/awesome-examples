package pro.gorodnyuk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Data // не нужно использовать эту аннотацию см. https://deinum.biz/2019-02-13-Lombok-Data-Ojects-Arent-Entities/
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "model")
    private String model;

    @Enumerated(value = EnumType.STRING)
    private CarType type;

    @Lob // если текст слишком 'жирный'
    @Column(name = "description")
    private String description;

    @ManyToOne
    private Person person; // это FK, тк со стороны 'многие'
}
