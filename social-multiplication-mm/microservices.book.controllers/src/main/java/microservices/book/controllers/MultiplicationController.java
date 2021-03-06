package microservices.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.book.api.domain.Multiplication;
import microservices.book.api.services.multiplication.MultiplicationService;

@RestController
@RequestMapping("/multiplications")
final class MultiplicationController {

  private final MultiplicationService multiplicationService;

  @Autowired
  MultiplicationController(final MultiplicationService multiplicationService) {
    this.multiplicationService = multiplicationService;
  }

  @GetMapping("/random")
  Multiplication getRandomMultiplication() {
    return multiplicationService.createRandomMultiplication();
  }
}
