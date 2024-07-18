package bg.softuni.aop.example;

import bg.softuni.aop.IncredibleMachine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Example1Aspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(IncredibleMachine.class);

  @Pointcut("execution(* bg.softuni.aop.IncredibleMachine.echo(..))")
  void trackEchoCalled(){}

  @Pointcut("execution(* bg.softuni.aop.IncredibleMachine.concat(..))")
  void trackConcatCalled(){}

  @Before("trackEchoCalled()")
  public void beforeEchoCalled() {
    LOGGER.info("Before echo called...");
  }

  @After("trackEchoCalled()")
  public void afterEchoCalled() {
    LOGGER.info("After echo called...");
  }

  // the pointcut expressions can be inside the advice annotation
  @AfterThrowing(pointcut = "execution(* bg.softuni.aop.IncredibleMachine.boom())",
            throwing = "error")
  public void onError(JoinPoint joinPoint,
      Throwable error) {
      LOGGER.info("We have thrown an error in the method {}",
          joinPoint.getSignature(),
          error);
  }

  @Around(value = "trackConcatCalled() && args(a, b)",
    argNames = "pjp,a,b")
  public String onConcat(
      ProceedingJoinPoint pjp,
      String a,
      String b) throws Throwable {

    // before
    LOGGER.info("Method concat was called with argumets {} and {}", a, b);
    String modifiedA = "(" + a + ")";
    String modifiedB = "(" + b + ")";

    //(Hello) (world)
    // execute
    String result = (String)pjp.proceed(new Object[]{modifiedA, modifiedB});
    // (Hello)~(world)

    // after
    return  "[" + result + "]";
    //[(Hello)~(world)]
  }
}
