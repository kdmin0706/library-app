package com.group.libraryapp.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.response.UserResponse;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceV2Test {

  @Autowired
  private UserServiceV2 userServiceV2;

  @Autowired
  private UserRepository userRepository;

  @AfterEach
  void tearDown() {
    userRepository.deleteAllInBatch();
  }

  @Test
  @DisplayName("모든 유저의 정보를 조회한다.")
  void createUsers() {
    // given
    User user1 = createUser("user1", 10);
    User user2 = createUser("user2", 20);
    User user3 = createUser("user3", 30);

    userRepository.saveAll(List.of(user1, user2, user3));

    // when
    List<UserResponse> responses = userServiceV2.getUsers();

    // then
    assertThat(responses).hasSize(3)
        .extracting("name", "age")
        .containsExactlyInAnyOrder(
            tuple("user1", 10),
            tuple("user2", 20),
            tuple("user3", 30)
        );
  }

  private User createUser(String name, int age) {
    return new User(name, age);
  }


}