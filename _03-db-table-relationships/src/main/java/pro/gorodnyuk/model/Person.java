package pro.gorodnyuk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//@Data // не нужно использовать эту аннотацию см. https://deinum.biz/2019-02-13-Lombok-Data-Ojects-Arent-Entities/
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    // класс будет 'расскрываться' благодаря аннотации @Embeddable внутри класса Address
    private Address address;

    @Transient // поле, которое не нужно отображать в БД
    private String extra;

    @OneToOne
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @OneToMany(mappedBy = "person")
    private List<Car> cars = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "person_gym", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "gym_id"))
    private List<Gym> gyms = new ArrayList<>();
}
