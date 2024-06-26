package com.group.libraryapp.domain.user.loanHistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

  boolean existsByBookNameAndIsReturn(String name, boolean isReturn);

}
