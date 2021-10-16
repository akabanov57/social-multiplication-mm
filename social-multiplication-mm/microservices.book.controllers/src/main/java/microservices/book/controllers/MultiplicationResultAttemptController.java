package microservices.book.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import microservices.book.api.domain.MultiplicationAttempt;
import microservices.book.api.domain.MultiplicationStatistic;
import microservices.book.api.services.multiplication.MultiplicationService;

/** This class provides a REST API to POST the attempts from users. */
@RestController
@RequestMapping("/results")
final class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    @Autowired
    MultiplicationResultAttemptController(final MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @PostMapping
    ResponseEntity<ResultResponse> postResult(
            @RequestBody MultiplicationAttempt attempt) {
        return ResponseEntity.ok(
                new ResultResponse(
                        multiplicationService.checkAttempt(attempt)));
    }

    static final class ResultResponse {

        private final boolean correct;

        ResultResponse(boolean correct) {
            this.correct = correct;
        }

        public boolean isCorrect() {
            return correct;
        }
    }

    @GetMapping
    ResponseEntity<List<MultiplicationStatistic>> getStatistics(
            @RequestParam("alias") String alias) {
        return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
    }
}
