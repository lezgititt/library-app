package com.group.libraryapp.domain.book;

import com.group.libraryapp.domain.user.User;
import jakarta.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    //user_id 대신 연관관계를 이용해서 User을 갖다 썼어. 그리고 서로가 서로를 인식하게 했어. 근데 테이블에서 user_id로 인식할게 있어야 되는데 그건 뭐지?
    //User 객체를 다 가져오니까 그 안에 자연스럽게 user_id가 있겠네
    @ManyToOne
    private User user;

    private String bookName;
    private boolean isReturn;

    protected UserLoanHistory(){}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
    }

    public void doReturn() {
        isReturn = true;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public String getBookName() {
        return this.bookName;
    }
}
