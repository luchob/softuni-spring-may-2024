package bg.softuni.student.repository;

import bg.softuni.student.model.Student;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryStudentRepository implements StudentRepository, BeanNameAware,
    InitializingBean, DisposableBean {

    private String name;

    public InMemoryStudentRepository() {
        System.out.println("Instantiating... ");
    }

    private final List<Student> students = List.of(
            new Student("Nina Bojinova", LocalDate.of(1977, 12, 9)),
            new Student("Lachezar Balev", LocalDate.of(1979, 3, 7)),
            new Student("Lachezar Balev Fake", LocalDate.of(1979, 3, 7))
    );

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Clean up of in memory student repo. Bye!");
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.name + " manages " + getAllStudents().size() + " students.");
    }
}
