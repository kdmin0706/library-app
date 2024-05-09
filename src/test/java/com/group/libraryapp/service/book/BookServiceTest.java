package com.group.libraryapp.service.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class BookServiceTest {

  @Autowired
  private BookService bookService;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserLoanHistoryRepository userLoanHistoryRepository;

  @AfterEach
  void tearDown() {
    userLoanHistoryRepository.deleteAllInBatch();
    bookRepository.deleteAllInBatch();
    userRepository.deleteAllInBatch();
  }

  @Test
  @DisplayName("사용자는 책을 빌릴 수 있습니다.")
  void loanBook() {
    // given
    Book book = new Book("springboot");
    bookRepository.save(book);

    User user = new User("user1", 20);
    userRepository.save(user);

    BookLoanRequest request = new BookLoanRequest(user.getName(), book.getName());

    // when
    bookService.loanBook(request);

    // then
    assertThat(userLoanHistoryRepository
        .existsByBookNameAndIsReturn(book.getName(), false)).isTrue();
  }

  @Test
  @DisplayName("책이 대출되어 있는 경우 예외 처리합니다.")
  void loanBookWhenIsAlreadyLoan() {
    // given
    Book book = new Book("springboot");
    bookRepository.save(book);

    User user = new User("user1", 20);
    userRepository.save(user);

    UserLoanHistory loanHistory = new UserLoanHistory(user, book.getName());
    userLoanHistoryRepository.save(loanHistory);

    // when
    BookLoanRequest request = new BookLoanRequest(user.getName(), book.getName());

    // then
    assertThatThrownBy(() -> bookService.loanBook(request))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("진작 대출되어 있는 책입니다.");
  }

}