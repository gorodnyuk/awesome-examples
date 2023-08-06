package pro.gorodnyuk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.gorodnyuk.model.Person;
import pro.gorodnyuk.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final DefaultGenerator defaultGenerator;
    private final PersonRepository personRepository;
    private final RestTemplate restTemplate;

    public PersonService(DefaultGenerator defaultGenerator, PersonRepository personRepository) {
        this.defaultGenerator = defaultGenerator;
        this.personRepository = personRepository;
        this.restTemplate = new RestTemplate();
    }

    /**
     * Взять сущность из текущего проекта
     *
     * @return person
     */
    public Person getDefaultPerson() {
        return defaultGenerator.getDefaultPerson();
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
        return restTemplate.getForObject("http:localhost:9090/person", Person.class);
    }
}
