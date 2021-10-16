package microservices.book.impl.services.multiplication;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import microservices.book.api.services.multiplication.RandomGeneratorService;

/**
 * We can use simple junit test here!!!
 *
 * @author andrei
 */
public class RandomGeneratorServiceTest {
	
    private RandomGeneratorService randomGeneratorService = new RandomGeneratorServiceImpl();

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception {
        // when a good sample of randomly generated factors is generated
        List<Integer> randomFactors =
                IntStream.range(0, 1000)
                        .map(i -> randomGeneratorService.generateRandomFactor())
                        .boxed()
                        .collect(Collectors.toList());

        // then all of them should be between 11 and 100
        // because we want a middle-complexity calculation
        assertThat(randomFactors)
                .isSubsetOf(IntStream.range(11, 100).boxed().collect(Collectors.toList()));
    }
}
