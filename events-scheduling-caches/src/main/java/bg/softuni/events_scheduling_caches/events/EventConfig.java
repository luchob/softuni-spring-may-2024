package bg.softuni.events_scheduling_caches.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class EventConfig {

  @Bean
  public ApplicationEventMulticaster applicationEventMulticaster() {
    SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
    simpleApplicationEventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
    return simpleApplicationEventMulticaster;
  }

}
