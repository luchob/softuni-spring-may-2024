package bg.softuni.mobilele.test;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestCache {

  @Cacheable(value = "person", key = "#firstName")
  public Person getPersons(String firstName, String lastName) {

    System.out.println("IN PERSONS");

    return new Person(firstName, lastName);
  }

  @CachePut(value = "person", key = "#firstName")
  public Person updatePerson(String firstName, String lastName) {
    System.out.println("IN UPDATE PERSONS");

    return new Person(firstName, lastName);
  }

  @CacheEvict(value = "person", key = "#firstName")
  public Person removePerson(String firstName, String lastName) {
    System.out.println("IN REMOVE PERSONS");

    return new Person(firstName, lastName);
  }

}

record Person(String firsName, String lastName) {}