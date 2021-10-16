package microservices.book.impl.services.multiplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import microservices.book.api.domain.Multiplication;
import microservices.book.api.domain.MultiplicationAttempt;
import microservices.book.api.domain.User;
import microservices.book.api.services.multiplication.MultiplicationService;
import microservices.book.api.services.multiplication.RandomGeneratorService;
import microservices.book.api.services.persistent.Database;

public class MultiplicationServiceTest {

    @Mock private RandomGeneratorService randomGeneratorService;

    @Mock private Database db;

    private MultiplicationService multiplicationService;

    private AutoCloseable mocks;

    @BeforeEach
    public void openMocks() {
        // With this call to initMocks we tell Mockito to process the annotations
        mocks = MockitoAnnotations.openMocks(this);
        multiplicationService = new MultiplicationServiceImpl(randomGeneratorService, db);
    }

    @AfterEach
    public void closeMocks() throws Exception {
        mocks.close();
    }

    @Test
    public void createRandomMultiplicationTest() {
        // given (our mocked Random Generator service will return first 50, then 30)
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);
        // when
        Multiplication multiplication = multiplicationService.createRandomMultiplication();
        // then
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationAttempt attempt =
                new MultiplicationAttempt(user, multiplication, 3000);
        // when
        boolean attemptResult = multiplicationService.checkAttempt(attempt);
        // assert
        assertThat(attemptResult).isTrue();
    }

    @Test
    public void checkWrongAttemptTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationAttempt attempt =
                new MultiplicationAttempt(user, multiplication, 3010);
        // when
        boolean attemptResult = multiplicationService.checkAttempt(attempt);
        // assert
        assertThat(attemptResult).isFalse();
    }
}
