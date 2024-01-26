package com.sinanyagsagan.libraryapp.entity;

import com.sinanyagsagan.libraryapp.enums.BookOffice;
import jakarta.persistence.*;
import lombok.*;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_stock")
public class BookStock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "book_stock")
    private int bookStock;

    @Enumerated(value = EnumType.STRING)
    private BookOffice bookOffice;
}
