package bg.softuni;

import bg.softuni.student.StudentService;
import bg.softuni.student.StudentServiceImpl;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    StudentService studentService = new StudentServiceImpl();
    var students = studentService
        .findYoungestStudents()
        .stream()
        .map(Record::toString)
        .collect(Collectors.joining(","));

    System.out.println("Students " + students);
  }

}
