package bg.softuni.student.config;

import bg.softuni.student.StudentService;
import bg.softuni.student.StudentServiceImpl;
import bg.softuni.student.repository.FileStudentRepository;
import bg.softuni.student.repository.InMemoryStudentRepository;
import bg.softuni.student.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//  @Bean(
//      name = "fileRepo"
//  )
//  public StudentRepository studentRepository() {
//    return new InMemoryStudentRepository();
//  }
//
//  @Bean
//  public StudentService studentService(StudentRepository studentRepository) {
//    return new StudentServiceImpl(studentRepository);
//  }
}
