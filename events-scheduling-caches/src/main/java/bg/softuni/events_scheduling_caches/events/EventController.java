package bg.softuni.events_scheduling_caches.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

  private final ApplicationEventPublisher applicationEventPublisher;

  public EventController(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @GetMapping("/fire-event")
  public String fireEvent(@RequestParam("name") String name) {

    System.out.println("Step 1. " + Thread.currentThread().getName());
    applicationEventPublisher.publishEvent(new HelloWorldEvent(this, "Hello, " + name));
    System.out.println("Step 2. "  + Thread.currentThread().getName());

    return "OK";
  }
}
