package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.book.UserLoanHistory;
import com.group.libraryapp.domain.book.UserLoanHistoryRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    //bookRepository가 spring bean에 등록돼있기 때문에 객체 생성하지 않고 바로 정의해줘도 스프링에서 알아서 값을 넣어줌.
    //new로 객체 만들면 스프링에서 넣어준 게 아니라 아예 새로운 객체가 생김.
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;

    //생성자로 객체를 만들어주기까지는 해야됨. 생성자로 만든 객체에 스프링이 자동으로 값을 넣어주는 방식.
    public BookService(BookRepository bookRepository, UserRepository userRepository, UserLoanHistoryRepository userLoanHistoryRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
    }

    @Transactional
    public void createBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request){
        //책 있는지 없는지 확인
        Book book = bookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);
        //책 대출여부 확인
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(), false)){
            throw new IllegalArgumentException("현재 대출된 책입니다.");
        };
        //유저 있는지 없는지 확인
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        //책 대출 기록
//        userLoanHistoryRepository.save(new UserLoanHistory(user,book.getName()));
        user.loanBook(book.getName());
    }

    @Transactional
    public void returnBook(BookReturnRequest request){
//        //username을 받아서 있는지 확인 -> 있으면 user_id 반환
//        Long user_id = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new).getId();
//        //user_id와 book name이 부합하는 항 있는지 확인 -> 있으면 반납처리.
//        UserLoanHistory returning = userLoanHistoryRepository.findByUserIdAndBookName(user_id, request.getBookName()).orElseThrow(IllegalArgumentException::new);
//        returning.doReturn();

        //user id를 반환하고, 그 반환된 애들 중에서 찾기 보다는, user 객체 자체를 반환. 그러면 연관관게에 있는 db들 사용하기 쉽네.
        //id에 해당하는 애들 찾고, 모든 userloanhistory 뒤지는 것보다, 그냥 id에 해당하는 애들 리스트 안에서 요청 처리하는게 더 빠름
//      // (1번 조건 -> 1&2번 모두 조건) vs (1번조건 -> 1번조건 내에서 2번조건 검사)
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        user.returnBook(request.getBookName());
    }
}
