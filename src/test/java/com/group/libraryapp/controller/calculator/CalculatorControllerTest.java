package com.group.libraryapp.controller.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.libraryapp.dto.caculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.caculator.request.CalculatorMultiplyRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("두 개의 수를 덧셈한다.")
  void addTwoNumbers() throws Exception {
    // given
    CalculatorAddRequest request = new CalculatorAddRequest(100, 200);

    // when // then
    mockMvc.perform(get("/add")
            .param("number1", Integer.toString(request.getNumber1()))
            .param("number2", Integer.toString(request.getNumber2()))
        )
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("두 개의 수를 곱셈한다.")
  void multiplyTwoNumbers() throws Exception {
    // given
    CalculatorMultiplyRequest request = new CalculatorMultiplyRequest(10, 20);

    // when // then
    mockMvc.perform(
        post("/multiply")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
    )
        .andDo(print())
        .andExpect(status().isOk());
  }

}