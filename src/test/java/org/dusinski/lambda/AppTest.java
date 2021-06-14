package org.dusinski.lambda;


import org.dusinski.basics.lambda.BlockLambdaExpression;
import org.dusinski.basics.lambda.ClassWithLambdaArgument;
import org.dusinski.basics.lambda.GenericLambdaExpression;
import org.dusinski.basics.lambda.IntPredicate;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;


public class AppTest {
    @Test
    public void testSingleLambdaExpression() {
        GenericLambdaExpression<Integer> isBigger = (x, y) -> x < y;
        GenericLambdaExpression<Integer> isEqual = (x, y) -> x == y;

        int a = 12;
        int b = 11;

        assertFalse(isBigger.doTest(a, b));
        assertTrue(isBigger.doTest(b, a));
        assertFalse(isEqual.doTest(a, b));
    }

    @Test
    public void testBlockLambdaExpression() {
        BlockLambdaExpression pivotString = input -> {
            StringBuilder result = new StringBuilder();
            for (int i = input.length() - 1; i >= 0; i--) {
                result.append(input.charAt(i));
            }
            return result.toString();
        };
        String aa = "abcdef";
        assertEquals("abcdef", aa);
        assertEquals("fedcba", pivotString.modifyString(aa));
    }


    @Test
    public void testGenericLambdaExpression() {
        Integer firstValue = 12;
        Integer secondValue = 13;
        GenericLambdaExpression<Integer> isGreater = (x, y) -> x > y;

        assertFalse(isGreater.doTest(firstValue, secondValue));

        String firstString = "aaaaa";
        String secondString = "aaaa";

        GenericLambdaExpression<String> isLonger = (a, b) -> a.length() > b.length();
        assertTrue(isLonger.doTest(firstString, secondString));
    }

    @Test
    public void testLambdaArgument() {
        final String inputString = "Test Welcome STRING";

        BlockLambdaExpression uppercase = x -> x.toUpperCase(Locale.ROOT);
        BlockLambdaExpression lowercase = x -> x.toLowerCase(Locale.ROOT);
        BlockLambdaExpression mirror = x -> {
            StringBuilder result = new StringBuilder();
            for (int i = x.length() - 1; i >= 0; i--) {
                result.append(x.charAt(i));
            }
            return result.toString();
        };


        Assert.assertEquals("TEST WELCOME STRING", ClassWithLambdaArgument.doAction(uppercase, inputString));
        Assert.assertEquals("test welcome string", ClassWithLambdaArgument.doAction(lowercase, inputString));
        Assert.assertEquals("GNIRTS emocleW tseT", ClassWithLambdaArgument.doAction(mirror, inputString));
    }


    @Test
    public void testLambdaPredicate() {
        int a = 14;
        assertTrue(ClassWithLambdaArgument.checkPredicate(IntPredicate::isPositive, a));
        assertTrue(ClassWithLambdaArgument.checkPredicate(IntPredicate::isEven, a));
        assertFalse(ClassWithLambdaArgument.checkPredicate(IntPredicate::isPrime, a));
    }
}
