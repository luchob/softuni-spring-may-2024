package bg.softuni.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IncredibleMachine {

  private static final Logger LOGGER = LoggerFactory.getLogger(IncredibleMachine.class);

  public void boom() {
    throw new NullPointerException("I made a bug");
  }

  public void echo(String echo) {
    LOGGER.info("Echo {}", echo);
  }

  public String concat(String a, String b) {
    return a + "~" + b;
  }
}
