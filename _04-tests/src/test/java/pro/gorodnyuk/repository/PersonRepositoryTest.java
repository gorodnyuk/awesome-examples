package pro.gorodnyuk.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pro.gorodnyuk.model.Person;

import java.util.Optional;

/**
 * Пример теста для Spring Data JPA.
 * Тут используется одна H2 база данных для запуска и теста.
 * Для использования в реальном проекте для тестирования необходимо указать для зависимости h2 в pom.xml (maven):
 * <scope>test</scope>
 */
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldGetPersonByNickName() {
        Optional<Person> maybePerson = personRepository.findByNickName("xxx_ivan_xxx");

        Assertions.assertTrue(maybePerson.isPresent());
        Person actual = maybePerson.get();
        Person expected = Person.builder()
                .id(1L)
                .name("Ivanov Ivan Ivanovich")
                .nickName("xxx_ivan_xxx")
                .address("Moscow, 7")
                .build();
        Assertions.assertEquals(expected, actual);
    }
}