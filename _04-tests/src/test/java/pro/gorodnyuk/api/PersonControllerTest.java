package pro.gorodnyuk.api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pro.gorodnyuk.model.Person;
import pro.gorodnyuk.service.PersonService;

import java.util.List;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    public void shouldGetPersonByNickName() throws Exception {
        String nickName = "xxx_ivan_xxx";
        Person person = Person.builder()
                .id(1L)
                .name("Ivan")
                .nickName(nickName)
                .address("Moscow, 7")
                .build();

        Mockito.doReturn(person).when(personService).getPersonByNickName(nickName);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/{nickName}", nickName))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Ivan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickName", Matchers.is(nickName)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.is("Moscow, 7")));
    }

    @Test
    public void shouldGetAllPersonsFromDataBase() throws Exception {
        List<Person> persons = List.of(
                Person.builder()
                        .id(1L)
                        .name("Ivan")
                        .nickName("xxx_ivan_xxx")
                        .address("Moscow, 7")
                        .build(),
                Person.builder()
                        .id(2L)
                        .name("Petr")
                        .nickName("xxx_petr_xxx")
                        .address("Samara, 2")
                        .build(),
                Person.builder()
                        .id(3L)
                        .name("Sidr")
                        .nickName("xxx_sidr_xxx")
                        .address("Irkutsk, 89")
                        .build()
        );

        Mockito.doReturn(persons).when(personService).getAllPersons();

        mockMvc.perform(MockMvcRequestBuilders.get("/api"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.containsInAnyOrder(1, 2, 3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name", Matchers.containsInAnyOrder("Ivan", "Petr", "Sidr")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].nickName",
                        Matchers.containsInAnyOrder("xxx_ivan_xxx", "xxx_petr_xxx", "xxx_sidr_xxx")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address",
                        Matchers.containsInAnyOrder("Moscow, 7", "Samara, 2", "Irkutsk, 89")));

    }
}