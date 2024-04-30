package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final JdbcTemplate jdbcTemplate;

  public UserController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @PostMapping("/user")
  public void saveUser(@RequestBody UserCreateRequest request) {
    String sql = "insert into user (name, age) values (?, ?)";
    jdbcTemplate.update(sql, request.getName(), request.getAge());
  }

  @GetMapping("/user")
  public List<UserResponse> getUsers() {
    String sql = "select * from user";
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      long id = rs.getLong("id");
      String name = rs.getString("name");
      int age = rs.getInt("age");

      return new UserResponse(id, name, age);
    });
  }

}
