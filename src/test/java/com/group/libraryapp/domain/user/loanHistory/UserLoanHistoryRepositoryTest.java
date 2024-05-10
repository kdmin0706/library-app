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
  void existsByBookNameAndIsReturn_whenIsLoaned() {
    // given
    String bookName = "SpringBoot";

    User user = createUser("user1", 30);
    userRepository.save(user);

    UserLoanHistory loanHistory = new UserLoanHistory(user, bookName);
    userLoanHistoryRepository.save(loanHistory);

    // when
    boolean result = userLoanHistoryRepository
        .existsByBookNameAndIsReturn(bookName, false);

    // then
    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("책의 이름을 조회하여 반납 여부를 체크한다.")
  void existsByBookNameAndIsReturn_whenIsReturned() {
    // given
    String bookName = "SpringBoot";

    User user = createUser("user", 20);
    userRepository.save(user);

    UserLoanHistory loanHistory = new UserLoanHistory(user, bookName);

    loanHistory.doReturn();
    userLoanHistoryRepository.save(loanHistory);

    // when
    boolean result = userLoanHistoryRepository
        .existsByBookNameAndIsReturn(bookName, true);

    // then
    assertThat(result).isTrue();
  }

  private User createUser(String name, int age) {
    return new User(name, age);
  }

}