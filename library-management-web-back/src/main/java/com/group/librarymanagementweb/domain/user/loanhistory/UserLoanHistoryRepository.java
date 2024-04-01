package com.group.librarymanagementweb.domain.user.loanhistory;

import com.group.librarymanagementweb.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    boolean existsByBookIdAndIsReturn(long bookId, boolean isReturn);

    List<UserLoanHistory> findAllByUserId(long userId);
}
