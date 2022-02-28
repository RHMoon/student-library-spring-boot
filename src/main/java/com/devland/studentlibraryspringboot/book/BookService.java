package com.devland.studentlibraryspringboot.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    public Book findById(Long bookId) {
        Optional<Book> book = this.bookRepository.findById(bookId);

        if(book.isEmpty()) {
            throw new BookNotFoundException();
        }

        return book.get();
    }

    public Book createBook(Book book) {
        return this.bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        Book existingBook = findById(book.getId());
        return this.bookRepository.save(existingBook);
    }

    public void bookBorrows(Book book) {
        book.setBorrowed(!book.isBorrowed());
        updateBook(book);
    }

    public Book changeStatusBook(Book book) {
        book.setBorrowed(!book.isBorrowed());
        return updateBook(book);
    }
}
