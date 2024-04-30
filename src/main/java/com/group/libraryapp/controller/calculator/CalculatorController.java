package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.caculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.caculator.request.CalculatorMultiplyRequest;
import com.group.libraryapp.dto.caculator.request.CalculatorSumRequest;
import com.group.libraryapp.dto.caculator.response.CalculatorResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

  @GetMapping("/api/v1/calc")
  public CalculatorResponse addTwoNumbers(CalculatorAddRequest request) {
    return new CalculatorResponse(
        request.getNumber1() + request.getNumber2(),
        request.getNumber1() - request.getNumber2(),
        request.getNumber1() * request.getNumber2()
    );
  }

  @PostMapping("/multiply")
  public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
    return request.getNumber1() * request.getNumber2();
  }

  @GetMapping("/api/v1/day-of-the-week")
  public Map<String, Object> getDayOfTheWeek(@RequestParam String date) {
    LocalDate localDate = LocalDate.parse(date);
    String sDate = localDate.getDayOfWeek().toString();
    return Map.of("dayOfTheWeek", sDate.substring(0, 3));
  }

  @PostMapping("/api/v1/sum")
  public int addAllNumbers(@RequestBody CalculatorSumRequest request) {
    List<Integer> numbers = request.getNumbers();
    return numbers.stream()
        .mapToInt(Integer::intValue).sum();
  }

}
