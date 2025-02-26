package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.book.UserLoanHistory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    //primary key
    @Id
    //자동생성(autoincrement)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    @Column(nullable = false, length = 20) //name VARCHAR(20)
    private String name;

    private Integer age;

    //기본 생성자가 있어야만 jpa객체를 만들 수가 있음.
    protected User() {}

    public User(String name, Integer age){
        if(name==null || name.isBlank()){
            //throw는 예외처리
            // IllegalArgumentException은 잘못된 인자가 들어왔을 때 발생하는 표준 예외 클래스

            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName){
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }
}
