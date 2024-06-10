package bg.softuni.mobilele.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  @EventListener(TestEvent.class)
  public void onTestEvent(TestEvent event) {
    System.out.println("ON TEST EVENT: " +
        Thread.currentThread().getName()
    );
  }
}
