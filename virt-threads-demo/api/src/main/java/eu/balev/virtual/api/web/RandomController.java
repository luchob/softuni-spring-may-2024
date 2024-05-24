package eu.balev.virtual.api.web;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomController {

  private static Logger LOGGER = LoggerFactory.getLogger(RandomController.class);

  @GetMapping("/random")
  public Integer random() {

    LOGGER.info("Requesting a random number");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return new Random().nextInt(1, 50);
  }

}
