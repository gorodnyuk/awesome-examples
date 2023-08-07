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
}