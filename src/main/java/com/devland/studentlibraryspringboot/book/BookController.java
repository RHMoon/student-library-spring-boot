package com.devland.studentlibraryspringboot.book;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class BookController {
 
    private final BookService bookService;

    private final ModelMapper modelMapper;

    // modelMapper.map(this, targetClass);
    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> getBooks() {
        List<Book> bookLists = this.bookService.getBooks();
        List<BookResponseDTO> bookResponseDTOs = bookLists.stream().map(
            book -> book.convertTo(this.modelMapper, BookResponseDTO.class))
            .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTOs);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable("id") Long bookId) {
        Book book = this.bookService.findById(bookId);
        
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(
            book.getId()
        );
        bookResponseDTO.setName(book.getName());
        bookResponseDTO.setBookBorrowingHistories(book.getBookBorrowingHistories());
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTO); 
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        Book book = Book.builder().name(bookRequestDTO.getBookName()).build();
        Book savedBook = this.bookService.createBook(book);
        BookResponseDTO bookResponseDTO = BookResponseDTO.builder().id(savedBook.getId()).name(savedBook.getName()).build();
        
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }
}
