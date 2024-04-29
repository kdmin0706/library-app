package com.group.libraryapp.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  @DisplayName("유저의 이름이 null인 경우 예외가 발생한다.")
  void UserNameWhenIsNull() {
    // when // then
    assertThatThrownBy(() -> new User(null, 15))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("잘못된 name(null)이 들어왔습니다.");

  }

  @Test
  @DisplayName("유저의 이름이 빈 문자열인 경우 예외가 발생한다.")
  void UserNameWhenIsBlank() {
    // when // then
    assertThatThrownBy(() -> new User("", 15))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("잘못된 name()이 들어왔습니다.");

  }

}