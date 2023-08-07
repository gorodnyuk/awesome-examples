package pro.gorodnyuk.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.gorodnyuk.model.Person;

/**
 * Самодостаточный тест без чего-то лишнего
 */
public class DefaultGeneratorTest {
    private DefaultGenerator defaultGenerator;

    @BeforeEach
    public void setup() {
        defaultGenerator = new DefaultGenerator();
    }

    @Test
    public void shouldGenerateDefaultPerson() {
        Person actual = defaultGenerator.getDefaultPerson();

        Person expected = Person.builder()
                .id(999L)
                .name("Oleg")
                .nickName("xxx_oleg_xxx")
                .address("Moscow, default 1")
                .build();

        Assertions.assertEquals(expected, actual);
    }
}