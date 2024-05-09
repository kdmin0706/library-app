package com.group.libraryapp.domain.user.loanHistory;

import static org.assertj.core.api.Assertions.assertThat;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserLoanHistoryRepositoryTest {

  @Autowired
  private UserLoanHistoryRepository userLoanHistoryRepository;

  @Autowired
  private UserRepository userRepository;

  @AfterEach
  void tearDown() {
    userLoanHistoryRepository.deleteAllInBatch();
    userRepository.deleteAllInBatch();
  }

  @Test
  @DisplayName("책의 이름을 조회하여 대출 여부를 체크한다.")
  void existsByBookNameAndIsReturn() {
    // given
    String bookName = "SpringBoot";

    User user = new User("user1", 20);
    userRepository.save(user);

    UserLoanHistory loanHistory = new UserLoanHistory(user, bookName);
    userLoanHistoryRepository.save(loanHistory);

    // when
    boolean result = userLoanHistoryRepository
        .existsByBookNameAndIsReturn(bookName, false);

    // then
    assertThat(result).isTrue();
  }

}