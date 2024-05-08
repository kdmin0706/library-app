package com.group.libraryapp.domain.user.loanHistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  private String bookName;

  private boolean isReturn;

  protected UserLoanHistory() {

  }

  public UserLoanHistory(Long userId, String bookName) {
    this.userId = userId;
    this.bookName = bookName;
    this.isReturn = false;
  }

  public void doReturn() {
    this.isReturn = true;
  }

}
