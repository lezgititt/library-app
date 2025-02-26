package com.group.libraryapp.domain.book;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //초깃값을 null로 둬야 새로운 entity로 인식됨. id=0이 되면 새로운 entity가 아니라고 판단함.
    private Long id = null;

    @Column(nullable = false, length = 255)
    private String name;

    protected Book(){}

    public Book(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
