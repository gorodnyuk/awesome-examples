package pro.gorodnyuk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//@Data // не нужно использовать эту аннотацию см. https://deinum.biz/2019-02-13-Lombok-Data-Ojects-Arent-Entities/
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "series")
    private String series;

    @Column(name = "number")
    private String number;

    @Column(name = "unitCode")
    private String unitCode;

    @Column(name = "issues_by")
    private String issuesBy;

    @Column(name = "issues_date")
    private LocalDate issuesDate;

    @OneToOne(mappedBy = "passport")
    private Person person;
}
