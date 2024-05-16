package bg.softuni.student.scopes;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Counter {

  private int cnt;

  void inc() {
    cnt++;
  }

  int getCnt() {
    return cnt;
  }

}
