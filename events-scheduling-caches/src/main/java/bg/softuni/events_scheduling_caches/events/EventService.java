package bg.softuni.events_scheduling_caches.events;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  @EventListener
  public void onHelloWorldEvent(HelloWorldEvent helloWorldEvent) {
    System.out.println("In EventService: " + Thread.currentThread().getName());
  }

}
