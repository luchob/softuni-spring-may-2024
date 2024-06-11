package bg.softuni.events_scheduling_caches.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldEventListener implements ApplicationListener<HelloWorldEvent> {

  @Override
  public void onApplicationEvent(HelloWorldEvent event) {
    System.out.println("In HelloWorldEventListener: " + Thread.currentThread().getName());
  }
//
//  @Override
//  public boolean supportsAsyncExecution() {
//    return false;
//  }
}
