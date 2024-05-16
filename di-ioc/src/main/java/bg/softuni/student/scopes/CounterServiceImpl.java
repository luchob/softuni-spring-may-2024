package bg.softuni.student.scopes;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

@Service
public abstract class CounterServiceImpl implements CounterService {

  @Override
  public void count() {
    Counter counter1 = counter();
    Counter counter2 = counter();

    counter1.inc();
    counter2.inc();

    System.out.println("Counter1: " + counter1.getCnt());
    System.out.println("Counter2: " + counter2.getCnt());
  }

  @Lookup
  public abstract Counter counter();

}
