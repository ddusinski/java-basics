package org.dusinski.basics;


import org.dusinski.basics.lambda.ClassWithLambdaArgument;
import org.dusinski.basics.lambda.IntPredicate;

public class App {
    public static void main(String[] args) {

        int a = 14;

        System.out.println("Value: " + a + " is positive: " + ClassWithLambdaArgument.checkPredicate(IntPredicate::isPositive, a));
        System.out.println("Value: " + a + " is even: " + ClassWithLambdaArgument.checkPredicate(IntPredicate::isEven, a));
        System.out.println("Value: " + a + " is prime: " + ClassWithLambdaArgument.checkPredicate(IntPredicate::isPrime, a));

    }
}
