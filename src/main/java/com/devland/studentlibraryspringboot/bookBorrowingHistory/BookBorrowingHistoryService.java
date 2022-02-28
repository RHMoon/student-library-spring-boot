package com.devland.studentlibraryspringboot.bookBorrowingHistory;

import java.time.LocalDateTime;

import com.devland.studentlibraryspringboot.book.Book;
import com.devland.studentlibraryspringboot.book.BookAvailabilityException;
import com.devland.studentlibraryspringboot.book.BookService;
import com.devland.studentlibraryspringboot.student.Student;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookBorrowingHistoryService {
    
    private final BookService bookService;
    private final BookBorrowingHistoryRepository bookBorrowingHistoryRepository;

    public void createBookBorrowingHistory(Student student,
        BookBorrowingHistoryRequestDTO bookBorrowingHistoryRequestDTO) {
        
        Book book = this.bookService.findById(bookBorrowingHistoryRequestDTO.getBookId());
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        BookBorrowingHistory newBookBorrowingHistory = BookBorrowingHistory.builder().student(student).book(book).borrowedAt(currentDateTime).build();
        this.bookService.bookBorrows(book);

        this.bookBorrowingHistoryRepository.save(newBookBorrowingHistory);
    }

    public void returnBook(Student student,
        BookBorrowingHistoryRequestDTO bookBorrowingHistoryRequestDTO) {
        
        Book book = this.bookService.findById(bookBorrowingHistoryRequestDTO.getBookId());

        if (!book.isBorrowed()) {
            throw new BookAvailabilityException();
        }

        BookBorrowingHistory bookHistory = this.bookBorrowingHistoryRepository.findByBookAndStudent(book, student);
        LocalDateTime currentDateTime = LocalDateTime.now();
        bookHistory.setReturnedAt(currentDateTime);
        
        Book bookReturned = this.bookService.changeStatusBook(book);

        bookHistory.setBook(bookReturned);
        this.bookBorrowingHistoryRepository.save(bookHistory);
    }

}