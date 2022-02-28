package com.devland.studentlibraryspringboot;

import java.util.List;
import java.util.Optional;

import com.devland.studentlibraryspringboot.student.Student;
import com.devland.studentlibraryspringboot.student.StudentRepository;
import com.devland.studentlibraryspringboot.student.StudentService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class StudentServiceTests {
    @Autowired
    StudentService studentService;

    @MockBean
    StudentRepository studentRepository;

    @Test
    void getStudent_shouldReturnAllStudents_whenInvoked(){
        // Student paksi = new Student();
        // paksi.setFirstName("Paksi");
        // paksi.setLastName("Bumi");
        // paksi.setId(1l);
        // List<Student> students = List.of(paksi);
        // Mockito.when(this.studentRepository.findAll()).thenReturn(students);

        // List<Student> actualResult = this.studentService.getStudents();

        // // Assert only used when there is logic and we want to compare expected result
        // Assertions.assertEquals(students, actualResult);
        // Mockito.verify(this.studentRepository,Mockito.times(1)).findAll();

        // or do only, if only want to check eitehr the method invoke
        this.studentService.getStudents();
        Mockito.verify(this.studentRepository,Mockito.times(1)).findAll();
    }

    @Test
    void createStudent_shouldReturnSaveStudent_whenInvoked(){
        Student paksi = new Student();
        paksi.setFirstName("Paksi");
        paksi.setLastName("Bumi");
        paksi.setId(1l);
        // List<Student> students = List.of(paksi);
        Mockito.when(this.studentRepository.save(paksi)).thenReturn(paksi);
        this.studentService.createStudent(paksi);
        Mockito.verify(this.studentRepository,Mockito.times(1)).save(paksi);
    }


    // //if getStudentById directly find the id witout checking empty
    // @Test
    // void getStudentById_shouldReturnStudent_whenInvoked(){
    //     Long id = 1l;
    //     Student paksi = new Student(id, "Paksi", "Bumi");
    //     // List<Student> students = List.of(paksi);
    //     Mockito.when(this.studentRepository.getById(id)).thenReturn(paksi);
    //     this.studentService.findById(id);
    //     Mockito.verify(this.studentRepository,Mockito.times(1)).getById(id);
    // }

    @Test
    void getStudentById_shouldReturnStudent_whenInvoked(){
        Long id = 1l;
        Student paksi = new Student(id, "Paksi", "Bumi");
        // List<Student> students = List.of(paksi);
        Mockito.when(this.studentRepository.findById(id)).thenReturn(Optional.of(paksi));
        this.studentService.findById(id);
        Mockito.verify(this.studentRepository,Mockito.times(1)).findById(id);
    }

    @Test
    void getStudentById_shouldReturnError_whenInvokedAndNoData(){
        Long id = 1l;
        Student paksi = new Student(id, "", "");
        // List<Student> students = List.of(paksi);
        Mockito.when(this.studentRepository.findById(1L)).thenReturn(Optional.of(paksi));
        this.studentService.findById(id);
        Mockito.verify(this.studentRepository,Mockito.times(1)).findById(id);
    }
}
