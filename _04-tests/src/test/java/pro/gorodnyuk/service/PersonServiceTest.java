package pro.gorodnyuk.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import pro.gorodnyuk.model.Person;
import pro.gorodnyuk.repository.PersonRepository;

import java.util.Optional;

/**
 * Юнит тесты с использованием Mockito
 * Покрывается большинство важных сценариев
 */
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private DefaultGenerator defaultGenerator;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PersonService personService;

    // **************************************************
    // ********** Можно еще примерно так делать *********
    // **************************************************
//    @BeforeEach
//    public void setup() {
//        defaultGenerator = Mockito.mock(DefaultGenerator.class);
//        personRepository = Mockito.mock(PersonRepository.class);
//        personService = new PersonService();
//        personService.setDefaultGenerator(defaultGenerator);
//        personService.setPersonRepository(personRepository);
//    }

    @Test
    public void shouldGetDefaultPersonFromDefaultGenerator() {
        Person person = Person.builder()
                .id(999L)
                .name("Oleg")
                .nickName("xxx_oleg_xxx")
                .address("Moscow, default 1")
                .build();

        Mockito.doReturn(person).when(defaultGenerator).getDefaultPerson();

        Person actual = personService.getDefaultPerson();

        Assertions.assertEquals(person, actual);

        Mockito.verify(defaultGenerator).getDefaultPerson();
    }

    @Test
    public void shouldGetPersonByNickNameFromDataBase() {
        String nickName = "xxx_ivan_xxx";
        Person person = Person.builder()
                .id(1L)
                .name("Ivan")
                .nickName(nickName)
                .address("Moscow, 7")
                .build();

        Mockito.doReturn(Optional.of(person)).when(personRepository).findByNickName(nickName);

        Person actual = personService.getPersonByNickName(nickName);

        Assertions.assertEquals(person, actual);

        Mockito.verify(personRepository).findByNickName(nickName);
    }

    @Test
    public void shouldReturnNullWhenPersonByNickNameNotExistsInDataBase() {
        String nickName = "xxx_ivan_xxx";

        Mockito.doReturn(Optional.empty()).when(personRepository).findByNickName(nickName);

        Person actual = personService.getPersonByNickName(nickName);

        Assertions.assertNull(actual);

        Mockito.verify(personRepository).findByNickName(nickName);
    }

    @Test
    public void shouldGetPersonFromOtherSystem() {
        Person person = Person.builder()
                .id(1L)
                .name("Ivan")
                .nickName("xxx_ivan_xxx")
                .address("Moscow, 7")
                .build();

        Mockito.doReturn(person).when(restTemplate).getForObject("http://localhost:9090/person", Person.class);

        Person actual = personService.getPersonFromIntegration();

        Assertions.assertEquals(person, actual);

        Mockito.verify(restTemplate).getForObject("http://localhost:9090/person", Person.class);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenOtherSystemNotAvailable() {
        Mockito.doThrow(new RuntimeException("error")).when(restTemplate).getForObject("http://localhost:9090/person", Person.class);

        Assertions.assertThrows(RuntimeException.class, () -> personService.getPersonFromIntegration());

        Mockito.verify(restTemplate).getForObject("http://localhost:9090/person", Person.class);
    }
}