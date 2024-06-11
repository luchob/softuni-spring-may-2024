package bg.softuni.events_scheduling_caches.cache;

import bg.softuni.events_scheduling_caches.scheduling.CronScheduler;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

  @Cacheable("students")
  public List<StudentDTO> getAllStudents() {
    LOGGER.info("Inside getAllStudents()");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    LOGGER.info("Returning result from getAllStudents()");
    return List.of(
        new StudentDTO("Pehso", 32),
        new StudentDTO("Ani", 23)
    );
  }

  @CachePut("students")
  public List<StudentDTO> updateStudents() {
    LOGGER.info("Inside updateStudents()");

    return List.of(
        new StudentDTO("Gosho", 11),
        new StudentDTO("Milena", 12)
    );
  }

  @CacheEvict("students")
  public void removeStudentsFromCache(){}


  @Cacheable(value = "students", key="#name")
  public StudentDTO getStudentByName(String name) {
    LOGGER.info("GET STUDENT {}", name);
    return new StudentDTO(name, null);
  }

}
