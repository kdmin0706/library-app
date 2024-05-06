package com.group.libraryapp.controller.user;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UserServiceV1 userService;

  @Test
  @DisplayName("유저의 정보를 저장합니다.")
  void saveUser() throws Exception {
    // given
    UserCreateRequest request = new UserCreateRequest("user", 25);

    // when  // then
    mockMvc.perform(
        post("/user")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
    )
        .andDo(print())
        .andExpect(status().isOk())
    ;
  }

  @Test
  @DisplayName("유저의 정보를 조회합니다.")
  void getUsers() throws Exception {
    // given
    List<UserResponse> responses = List.of(
        new UserResponse(1, new User("user", 10))
    );

    given(userService.getUsers()).willReturn(responses);

    // when // then
    mockMvc.perform(
        get("/user")
    )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value("user"))
        .andExpect(jsonPath("$[0].age").value(10))
    ;
  }

}