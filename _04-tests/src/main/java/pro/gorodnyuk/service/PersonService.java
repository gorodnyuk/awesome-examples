package pro.gorodnyuk.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.gorodnyuk.model.Person;
import pro.gorodnyuk.repository.PersonRepository;

import java.util.List;

@Setter
@Service
public class PersonService {

    @Autowired
    private DefaultGenerator defaultGenerator;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DummyService dummyService;

    /**
     * Взять сущность из текущего проекта
     *
     * @return person
     */
    public Person getDefaultPerson() {
        return defaultGenerator.getDefaultPerson();
    }

    /**
     * Взять все сущности из базы данных
     *
     * @return persons list
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Взять сущность из базы данных
     *
     * @param nickName ник
     * @return person
     */
    public Person getPersonByNickName(String nickName) {
        return personRepository.findByNickName(nickName).orElse(null);
    }

    /**
     * Взять сущность из интеграции с другой системой
     *
     * @return person
     */
    public Person getPersonFromIntegration() {
        return restTemplate.getForObject("http://localhost:9090/person", Person.class);
    }

    /**
     * Пример Spy-объекта
     */
    public void dummy() {
        dummyService.dummy();
    }
}
