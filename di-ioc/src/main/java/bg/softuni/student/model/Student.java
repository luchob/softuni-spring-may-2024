package bg.softuni.student.model;

import java.time.LocalDate;
import java.util.Objects;

public record Student(String name, LocalDate birthDay) {

    public Student(String name, LocalDate birthDay) {
        this.name = Objects.requireNonNull(name);
        this.birthDay = Objects.requireNonNull(birthDay);
    }
}
