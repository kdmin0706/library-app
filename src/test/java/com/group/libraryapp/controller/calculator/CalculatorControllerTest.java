package com.group.libraryapp.controller.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.libraryapp.dto.caculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.caculator.request.CalculatorMultiplyRequest;
import com.group.libraryapp.dto.caculator.request.CalculatorSumRequest;
import java.util.List;
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
        .andExpect(status().isOk())
        .andExpect(content().string("300"))
    ;
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
        .andExpect(status().isOk())
        .andExpect(content().string("200"))
    ;
  }

  @Test
  @DisplayName("두 개의 수를 계산한다.")
  void calculateNumbers() throws Exception {
    // given
    CalculatorAddRequest request = new CalculatorAddRequest(20, 10);

    // when // then
    mockMvc.perform(get("/api/v1/calc")
            .param("number1", Integer.toString(request.getNumber1()))
            .param("number2", Integer.toString(request.getNumber2()))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.add").value(30))
        .andExpect(jsonPath("$.minus").value(10))
        .andExpect(jsonPath("$.multiply").value(200))
    ;
  }


  @Test
  @DisplayName("날짜에 맞는 요일을 반환한다.")
  void getDayOfTheWeek() throws Exception {
    // when  // then
    mockMvc.perform(
        get("/api/v1/day-of-the-week")
            .param("date", "2024-01-01")
    )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.dayOfTheWeek").value("MON"))
    ;
  }

  @Test
  @DisplayName("리스트에 있는 숫자를 모두 더한다.")
  void addAllNumbers() throws Exception {
    // given
    CalculatorSumRequest request = new CalculatorSumRequest(
        List.of(1,2,3,4,5)
    );

    // when // then
    mockMvc.perform(
        post("/api/v1/sum")
            .content(objectMapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON)
    )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("15"))
    ;
  }

}