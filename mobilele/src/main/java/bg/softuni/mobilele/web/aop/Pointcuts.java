package bg.softuni.mobilele.web.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
  @Pointcut("@annotation(WarnIfExecutionExceeds)")
  public void warnIfExecutionExceeds(){}
}
