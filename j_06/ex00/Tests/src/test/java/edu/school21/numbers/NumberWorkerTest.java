package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {
    NumberWorker testProgram;

    @BeforeEach
    void beforeEachMethod() {
        testProgram = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 7, 11, Integer.MAX_VALUE})
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(testProgram.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, Integer.MAX_VALUE - 1})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(testProgram.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -100, -1, Integer.MIN_VALUE})
    void  isPrimeForIncorrectNumbers(int number) {
        assertThrows(IllegalNumberException.class, () -> testProgram.isPrime(number), "IllegalNumberException was expected");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void checkDigitSum(int number, int sum) {
        assertEquals(sum, testProgram.digitsSum(number));
    }
}