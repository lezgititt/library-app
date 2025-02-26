package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory,Long> {
    boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);
}
