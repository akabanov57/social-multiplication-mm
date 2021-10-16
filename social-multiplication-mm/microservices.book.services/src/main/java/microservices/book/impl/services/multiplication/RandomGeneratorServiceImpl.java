package microservices.book.impl.services.multiplication;

import java.util.Random;

import org.springframework.stereotype.Service;

import microservices.book.api.services.multiplication.RandomGeneratorService;

@Service
class RandomGeneratorServiceImpl implements RandomGeneratorService {

    static final int MINIMUM_FACTOR = 11;

    static final int MAXIMUM_FACTOR = 99;

    @Override
    public int generateRandomFactor() {
        return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
    }
}
