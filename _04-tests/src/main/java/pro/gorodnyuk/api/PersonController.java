package pro.gorodnyuk.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.gorodnyuk.model.Person;
import pro.gorodnyuk.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    private List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{nickName}")
    public Person getPersonByNickName(@PathVariable("nickName") String nickName) {
        return personService.getPersonByNickName(nickName);
    }
}
