package org.dusinski.basics.lambda;

public class IntPredicate {

    public static boolean isEven(int input) {
        return input % 2 == 0;
    }

    public static boolean isPositive(int input) {
        return input > 0;
    }

    public static boolean isPrime(int input) {
        if (input < 2) {
            return false;
        }
        for (int i = 2; i < input/i; i++) {
            if ((input % i) == 0) {
                return false;
            }
        }
        return true;
    }

}
