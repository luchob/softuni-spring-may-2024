package bg.softuni.mobilele.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class TestConfig {

  @Bean(name = "applicationEventMulticaster")
  public ApplicationEventMulticaster applicationEventMulticaster() {

    SimpleApplicationEventMulticaster eventMulticaster =
        new SimpleApplicationEventMulticaster();

    eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());

    return eventMulticaster;
  }

}
