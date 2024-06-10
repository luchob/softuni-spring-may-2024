package bg.softuni.mobilele.test;

import java.util.List;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAsyncController {

  private final ApplicationEventPublisher eventPublisher;
  private final TestCache testCache;
  private final CacheManager cacheManager;

  public TestAsyncController(ApplicationEventPublisher eventPublisher,
      TestCache testCache,
      CacheManager cacheManager) {
    this.eventPublisher = eventPublisher;
    this.testCache = testCache;
    this.cacheManager = cacheManager;
  }

  @GetMapping("/test")
  public String test() {
//    eventPublisher.publishEvent(new TestEvent("Senko"));
//    System.out.println("Controller: " + Thread.currentThread().getName());

    System.out.println("Cache manager: " + cacheManager.getClass());

    Person person = testCache.getPersons("A", "B");
//    Person person1 = testCache.getPersons("A", "B2");
//    Person person2 = testCache.removePerson("A", "B2");
//    Person person3 = testCache.getPersons("A", "B3");
//
//    System.out.println(person);
//    System.out.println(person1);
//    System.out.println(person2);
//    System.out.println(person3);

    System.out.println(person);

    return "OK";
  }

}
