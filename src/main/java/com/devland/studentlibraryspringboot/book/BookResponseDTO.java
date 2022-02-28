package com.devland.studentlibraryspringboot.book;

import java.util.List;

import com.devland.studentlibraryspringboot.bookBorrowingHistory.BookBorrowingHistory;

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
public class BookResponseDTO {
    private Long id;
    private String name;
    private boolean isBorrowed;
    private List<BookBorrowingHistory> bookBorrowingHistories;
}
