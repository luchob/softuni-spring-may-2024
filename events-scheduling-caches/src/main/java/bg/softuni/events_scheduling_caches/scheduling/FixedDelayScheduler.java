package bg.softuni.events_scheduling_caches.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class FixedDelayScheduler {
  private int counter = 0;

  private final Logger LOGGER = LoggerFactory.getLogger(FixedDelayScheduler.class);

  @Scheduled(fixedDelay = 5000)
  public void onFixedDelay() throws InterruptedException {
    counter++;

    int toSleep = counter % 2 == 0 ? 2000 : 6000;

    LOGGER.info("Start of onFixedDelay(). To sleep: " + toSleep);

    Thread.sleep(toSleep);

    LOGGER.info("End of onFixedDelay()");
  }
}
