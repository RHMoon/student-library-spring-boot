package com.devland.studentlibraryspringboot.bookBorrowingHistory;

import com.devland.studentlibraryspringboot.book.Book;
import com.devland.studentlibraryspringboot.student.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowingHistoryRepository extends JpaRepository<BookBorrowingHistory, Long>{
    BookBorrowingHistory findByBookAndStudent(Book book, Student student);
}
