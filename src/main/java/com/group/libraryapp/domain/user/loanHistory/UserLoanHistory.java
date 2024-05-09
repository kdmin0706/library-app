package com.group.libraryapp.domain.user.loanHistory;

import com.group.libraryapp.domain.user.User;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserLoanHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  private String bookName;

  private boolean isReturn;

  public String getBookName() {
    return bookName;
  }

  protected UserLoanHistory() {

  }

  public UserLoanHistory(User user, String bookName) {
    this.user = user;
    this.bookName = bookName;
    this.isReturn = false;
  }

  public void doReturn() {
    this.isReturn = true;
  }

}
