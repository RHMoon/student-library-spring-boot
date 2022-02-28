package com.devland.studentlibraryspringboot.student;

// import java.util.List;

// import com.devland.studentlibraryspringboot.book.BookBorrowingHistory;

public class StudentResponseDTO {
    private long id;
    private String firstName;
    private String lastName;
    // private List<BookBorrowingHistory> bookBorrowingHistories;
    
    public StudentResponseDTO() {

    }

    public StudentResponseDTO(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
