package com.devland.studentlibraryspringboot.student;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.devland.studentlibraryspringboot.bookBorrowingHistory.BookBorrowingHistory;
// import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    // private int age;
    @JsonManagedReference
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<BookBorrowingHistory> bookBorrowingHistories;
    
    public Student() {
        
    }

    public Student(Long id, String firstName, String lastName, List<BookBorrowingHistory> bookBorrowingHistories) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookBorrowingHistories = bookBorrowingHistories;
    }

    public Student(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        // this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    // public int getAge() {
    //     return age;
    // }
    // public void setAge(int age) {
    //     this.age = age;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<BookBorrowingHistory> getBookBorrowingHistories() {
        return bookBorrowingHistories;
    }

    public void setBookBorrowingHistories(List<BookBorrowingHistory> bookBorrowingHistories) {
        this.bookBorrowingHistories = bookBorrowingHistories;
    }
}
