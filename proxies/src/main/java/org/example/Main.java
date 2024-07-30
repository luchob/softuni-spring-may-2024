package org.example;

import java.lang.reflect.Proxy;
import org.example.proxydemo.CacheableInvocationHandler;
import org.example.proxydemo.StudentService;
import org.example.proxydemo.StudentServiceImpl;

public class Main {

  public static void main(String[] args) {
    StudentService studentService = studentService();

    studentService.findStudents();
    studentService.findStudents();

    studentService.findStudents().forEach(
        System.out::println
    );

  }

  private static StudentService studentService() {
    return (StudentService) Proxy.newProxyInstance(
        Main.class.getClassLoader(),
        new Class[]{StudentService.class},
        new CacheableInvocationHandler(new StudentServiceImpl())
    );
  }
}