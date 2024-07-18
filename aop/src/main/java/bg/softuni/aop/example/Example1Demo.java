package bg.softuni.aop.example;

import bg.softuni.aop.IncredibleMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Example1Demo implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(IncredibleMachine.class);

  private final IncredibleMachine incredibleMachine;

  public Example1Demo(IncredibleMachine incredibleMachine) {
    this.incredibleMachine = incredibleMachine;
  }


  @Override
  public void run(String... args) throws Exception {
//    this.incredibleMachine.echo("Hello, world!");
//    try {
//      this.incredibleMachine.boom();
//    } catch (Exception e) {
//      // do not log
//    }
//
    String result = this.incredibleMachine.concat("Hello", "world");

    LOGGER.info("The result is: {}", result);
    //The result is: [(Hello)~(world)]
  }
}
