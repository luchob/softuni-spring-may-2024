package bg.softuni;

import bg.softuni.student.StudentService;
import bg.softuni.student.StudentServiceImpl;
import bg.softuni.student.config.AppConfig;
import bg.softuni.student.repository.InMemoryStudentRepository;
import bg.softuni.student.scopes.CounterService;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {

    ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("bg.softuni");

    context.registerShutdownHook();

//    StudentService studentService = context.getBean(StudentService.class);
//
//    System.out.println("Students " + studentService.findYoungestStudents());

    CounterService counterService = context.getBean(CounterService.class);
    counterService.count();

  }

}
