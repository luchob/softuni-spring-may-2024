package bg.softuni.mobilele.web.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MonitoringAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);

  @Around("Pointcuts.warnIfExecutionExceeds()")
  public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

    WarnIfExecutionExceeds annotation = getAnnotation(pjp);
    long timeout = annotation.timeInMillis();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    var returnValue = pjp.proceed();

    stopWatch.stop();

    if (stopWatch.lastTaskInfo().getTimeMillis() > timeout) {
      LOGGER.warn("The method {} ran for {} millis which is more than the " +
              "expected {} millis.",
          pjp.getSignature(),
          stopWatch.lastTaskInfo().getTimeMillis(),
          timeout
      );
    }

    return returnValue;
  }

  private static WarnIfExecutionExceeds getAnnotation(ProceedingJoinPoint pjp) {

    Method method = ((MethodSignature)pjp.getSignature()).getMethod();

    try {
      return pjp
          .getTarget()
          .getClass()
          .getMethod(method.getName(), method.getParameterTypes())
          .getAnnotation(WarnIfExecutionExceeds.class);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }


}
