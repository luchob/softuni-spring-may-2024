package bg.softuni.events_scheduling_caches.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class CronScheduler {

  private final Logger LOGGER = LoggerFactory.getLogger(CronScheduler.class);

  @Scheduled(cron = "*/10 * * * * *")
  public void onCron() {
    LOGGER.info("On cron");
  }

}
