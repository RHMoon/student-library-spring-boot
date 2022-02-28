package com.devland.studentlibraryspringboot.student;

import java.util.List;
// import java.util.Optional;
import java.util.stream.Collectors;

import com.devland.studentlibraryspringboot.bookBorrowingHistory.BookBorrowingHistoryRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    // @Autowired
    // private BookService bookService;

    // @GetMapping("/students")
    // public List<Student> getStudent() {
    //     return this.studentService.getAllStudents();    
    // }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudents() {
        List<Student> studentList = this.studentService.getStudents();

        List<StudentResponseDTO> studentResponseDTOList = studentList.stream().map(
            student -> new StudentResponseDTO(
                student.getId(), student.getFirstName(), student.getLastName())
            ).collect(Collectors.toList());
        
        return ResponseEntity.status(HttpStatus.OK).body(studentResponseDTOList);    
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable("id") Long studentId) {
        Student student = this.studentService.findById(studentId);
        
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
            student.getId(), student.getFirstName(), student.getLastName());
        return ResponseEntity.status(HttpStatus.OK).body(studentResponseDTO); 
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudentById(@PathVariable("id") Long Id, @RequestBody StudentRequestDTO studentRequestDTO){
        Student updatedStudent = studentService.updateStudentById(Id, studentRequestDTO);
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
            updatedStudent.getId(), updatedStudent.getFirstName(), updatedStudent.getLastName()
        );
        return ResponseEntity.status(HttpStatus.OK).body(studentResponseDTO);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequestDTO studentRequestDTO){
        Student newStudent = new Student();
        newStudent.setFirstName(studentRequestDTO.getFirstName());
        newStudent.setLastName(studentRequestDTO.getLastName());

        Student saveStudent = this.studentService.createStudent(newStudent);

        // StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
        //     newStudent.getId(), newStudent.getFirstName(), newStudent.getLastName()
        // );

        return ResponseEntity.status(HttpStatus.CREATED).body(saveStudent);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") Long Id){
        this.studentService.deleteById(Id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/students/{studentId}/borrowing")
    public ResponseEntity<Void> createBorrowingBooks(@PathVariable("studentId") Long studentId, @RequestBody BookBorrowingHistoryRequestDTO bookBorrowingHistoryRequestDTO) {
        this.studentService.createStudentBorrow(studentId, bookBorrowingHistoryRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/students/{studentId}/returning")
    public ResponseEntity<BookBorrowingHistoryRequestDTO> returningBook(@PathVariable("studentId") Long studentId, @RequestBody BookBorrowingHistoryRequestDTO bookBorrowingHistoryRequestDTO) {
        this.studentService.studentReturn(studentId, bookBorrowingHistoryRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookBorrowingHistoryRequestDTO);
    }

    // 2nd options
    // @PutMapping("/students/{studentId}/returning")
    // public ResponseEntity<Book> returningBook(@PathVariable("studentId") Long studentId, @RequestBody BookBorrowingHistoryRequestDTO bookBorrowingHistoryRequestDTO) {
    //     this.studentService.studentReturn(studentId, bookBorrowingHistoryRequestDTO);
    //     Book book = this.bookService.findById(bookBorrowingHistoryRequestDTO.getBookId());

    //     return ResponseEntity.status(HttpStatus.CREATED).body(book);
    // }
}