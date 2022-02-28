package com.devland.studentlibraryspringboot.book;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.devland.studentlibraryspringboot.bookBorrowingHistory.BookBorrowingHistory;
// import com.devland.studentlibraryspringboot.student.StudentBorrowingHistory;
import com.devland.studentlibraryspringboot.utils.Convertable;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book implements Convertable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isBorrowed;
    @JsonManagedReference
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookBorrowingHistory> bookBorrowingHistories;

    // @JsonManagedReference
    // @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    // private List<StudentBorrowingHistory> studentBorrowingHistories;
}
