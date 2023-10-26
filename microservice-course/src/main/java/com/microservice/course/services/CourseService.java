package com.microservice.course.services;

import com.microservice.course.client.StudentClient;
import com.microservice.course.entities.Course;
import com.microservice.course.entities.dto.StudentDto;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistence.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentClient studentClient;

    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentsByIdCourse(Long idCourse) {
        Course course = findById(idCourse);
        List<StudentDto> studentDtoList = studentClient.findAllStudentByCourse(idCourse);
        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDtoList(studentDtoList)
                .build();
    }
}
