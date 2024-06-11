package bg.softuni.events_scheduling_caches.events;

import org.springframework.context.ApplicationEvent;

public class HelloWorldEvent extends ApplicationEvent {

  private final String message;

  public HelloWorldEvent(Object source, String message) {
    super(source);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
