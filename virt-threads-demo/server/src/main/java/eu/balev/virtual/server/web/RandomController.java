package eu.balev.virtual.server.web;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class RandomController {

  private static class Statistics {

    private static AtomicInteger requests = new AtomicInteger();
    static void inc() {
      requests.incrementAndGet();
    }

    static void print() {
      if (requests.get() % 100 == 0) {
        System.out.println("Received " + requests.get() + " requests. " + LocalTime.now());
      }
    }
  }

  private final RestClient restClient;

  public RandomController(RestClient restClient) {
    this.restClient = restClient;
  }

  @GetMapping("/random")
  public Integer getRandom() {

    Integer result =  restClient
        .get()
        .uri("/random")
        .retrieve()
        .body(Integer.class);

    Statistics.inc();
    Statistics.print();

    return result;
  }
}
