package pro.gorodnyuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.gorodnyuk.model.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByNickName(String nickName);
}
