package flink;

import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

public class PrimeNumberFinder extends ProcessFunction<String, String> {

    @Override
    public void processElement(String value, ProcessFunction<String, String>.Context ctx, Collector<String> out) throws Exception {

        long primeNumberIndex = Long.parseLong(value);
        long currentIndex = 0;
        long currentNumber = 0;
        while (currentIndex < primeNumberIndex) {
            currentNumber++;
            if (isPrime(currentNumber)) {
                currentIndex++;
            }
        }
        out.collect(String.valueOf(currentNumber));
    }

    private boolean isPrime(long num) {
        boolean isPrime = true;
        if (num <= 0) {
            return false;
        }

        if (num == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); ++i) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }
}
