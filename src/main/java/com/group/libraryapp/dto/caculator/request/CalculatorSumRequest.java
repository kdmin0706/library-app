package com.group.libraryapp.dto.caculator.request;

import java.util.List;

public class CalculatorSumRequest {

  private List<Integer> numbers;

  public CalculatorSumRequest() {
  }

  public CalculatorSumRequest(List<Integer> numbers) {
    this.numbers = numbers;
  }

  public List<Integer> getNumbers() {
    return numbers;
  }
}
