package bg.softuni.mobilele.test;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TestApplicationEventListener implements ApplicationListener<TestEvent> {

  @Override
  public void onApplicationEvent(TestEvent event) {
    System.out.println("IN ApplicationListener " + Thread.currentThread().getName());
  }

  @Override
  public boolean supportsAsyncExecution() {
    return false;
  }
}
