package com.devland.studentlibraryspringboot.student;

import java.util.List;
// import java.util.Optional;
import java.util.Optional;

import com.devland.studentlibraryspringboot.bookBorrowingHistory.BookBorrowingHistoryRequestDTO;
import com.devland.studentlibraryspringboot.bookBorrowingHistory.BookBorrowingHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private BookBorrowingHistoryService bookBorrowingHistoryService;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // public List<Student> getAllStudents() {
    //     return List.of(new Student("Naufal","Farras",23),new Student("Ahmad","Fauzi",22));
    // }

    public Student createStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public Student findById(Long id) {
        Optional<Student> existingStudent = this.studentRepository.findById(id);

        if(existingStudent.isEmpty()) {
            throw new StudentNotFoundException();
        }
        return this.studentRepository.getById(id);
    }

    public Student updateStudentById(Long id, StudentRequestDTO studentRequestDTO) {
        Student studentData = studentRepository.findById(id).get();
        System.out.println(studentData.toString());
        studentData.setFirstName(studentRequestDTO.getFirstName());
        studentData.setLastName(studentRequestDTO.getLastName());
        return studentRepository.save(studentData);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public void createStudentBorrow(Long studentId, BookBorrowingHistoryRequestDTO bookBorrowingHistoryRequestDTO) {
        Student student = findById(studentId);
        this.bookBorrowingHistoryService.createBookBorrowingHistory(student, bookBorrowingHistoryRequestDTO);
    }

    public void studentReturn(Long studentId, BookBorrowingHistoryRequestDTO bookBorrowingHistoryRequestDTO) {
        Student student = findById(studentId);
        this.bookBorrowingHistoryService.returnBook(student, bookBorrowingHistoryRequestDTO);
    }
}
