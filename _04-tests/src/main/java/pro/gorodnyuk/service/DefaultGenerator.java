package pro.gorodnyuk.service;

import org.springframework.stereotype.Component;
import pro.gorodnyuk.model.Person;

@Component
public class DefaultGenerator {

    public Person getDefaultPerson() {
        return new Person(999L, "Oleg", "xxx_oleg_xxx", "Moscow, default 1");
    }
}
