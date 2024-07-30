package org.example.proxydemo;

import java.util.List;

public class StudentServiceImpl implements StudentService {
  @Override
  @Cacheable(value = "students")
  public List<StudentDTO> findStudents() {
    System.out.println("Calculating students....");
    return List.of(
        new StudentDTO(20, "Anna"),
        new StudentDTO(40, "Pehso")
    );
  }
}

