package com.sinanyagsagan.libraryapp.entity;

import com.sinanyagsagan.libraryapp.enums.BookCategory;
import jakarta.persistence.*;
import lombok.*;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_ısbn")
    private String bookISBN;

    @Column(name = "book_language")
    private String bookLanguage;

    @Column(name = "book_volume_no")
    private Long bookVolumeNo;

    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;

    @Column(name = "publisher")
    private String publisher;
}
