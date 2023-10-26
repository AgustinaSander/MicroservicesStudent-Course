package com.microservice.student.persistence;

import com.microservice.student.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    //No hace falta colocar la query ya que JPA detecta lo mismo por el patron en el nombre del metodo
    //@Query("SELECT s FROM Student s WHERE s.courseId =: idCourse")
    List<Student> findAllByCourseId(Long idCourse);
}
