package bg.softuni.mobilele.model.entity;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.ValueGenerationType;

@ValueGenerationType(generatedBy = UUIDSequenceGenerator.class)
@Retention(RUNTIME)
@Target({METHOD,FIELD})
public @interface UUIDSequence {
}