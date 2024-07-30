package org.example.proxydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheableInvocationHandler implements InvocationHandler {

  private final Map<String, Object> cache =
      new ConcurrentHashMap<>();

  private final StudentService delegate;

  public CacheableInvocationHandler(StudentService delegate) {
    this.delegate = delegate;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    Cacheable cacheableAnnotation = delegate
        .getClass()
        .getMethod(method.getName(), method.getParameterTypes())
        .getAnnotation(Cacheable.class);

    if (cacheableAnnotation != null) {
      String cacheKey = cacheableAnnotation.value();
      if (cache.containsKey(cacheKey)) {
        return cache.get(cacheKey);
      } else {
        Object result = method.invoke(delegate, args);
        cache.put(
            cacheKey,
            result
        );
        return result;
      }
    } else {
      return method.invoke(delegate, args);
    }
  }
}
