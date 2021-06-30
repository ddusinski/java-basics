package org.dusinski.lambda;


import org.dusinski.basics.lambda.BlockLambdaExpression;
import org.dusinski.basics.lambda.ClassWithLambdaArgument;
import org.dusinski.basics.lambda.GenericLambdaExpression;
import org.dusinski.basics.lambda.IntPredicate;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;
import java.util.Optional;
import java.util.function.*;

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

    @Test
    public void testPredefinedPredicate() {
        String input1 = "contains some spaces";
        String input2 = "DoNotContainsAnySpace";
        Predicate<String> containsSpaces = x -> x.contains(" ");
        assertTrue(containsSpaces.test(input1));
        assertFalse(containsSpaces.test(input2));

        int a = 12;
        int b = 13;
        Predicate<Integer> isMultipleOfTwo = x -> (x % 2) == 0;
        assertTrue(isMultipleOfTwo.test(a));
        assertFalse(isMultipleOfTwo.test(b));
    }

    @Test
    public void testUnaryOperator() {
        String input1 = "Contains Some Spaces";

        UnaryOperator<String> deleteSpaces = x -> x.replace(" ", "");
        UnaryOperator<String> changeSizes = x -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < x.length(); i++) {
                if (Character.isUpperCase(x.charAt(i))) {
                    result.append(Character.toLowerCase(x.charAt(i)));
                } else {
                    result.append(Character.toUpperCase(x.charAt(i)));
                }
            }
            return result.toString();
        };

        Assert.assertEquals("ContainsSomeSpaces", deleteSpaces.apply(input1));
        Assert.assertEquals("cONTAINS sOME sPACES", changeSizes.apply(input1));
    }

    @Test
    public void testFunctionalOperators() {

        String inputString = "The String Value";
        Integer integerValue = 102;

//        take parameter of one type and return parameter of the same type
        UnaryOperator<String> unaryOperatorUpperCase = x -> x.toUpperCase(Locale.ROOT);
        Assert.assertEquals("THE STRING VALUE", unaryOperatorUpperCase.apply(inputString));

//        takes parameter of one type and return value of another type
        Function<Integer, String> testFunctionIntegerToString = x -> { return x<100 ? "Less than 100":"Bigger than 100";};
        Assert.assertEquals("Bigger than 100", testFunctionIntegerToString.apply(integerValue));

// takes two different input parameters and return value of third type
        BiFunction<Integer, Double, String> testBiFunction = (a, b)-> "To get "+b + " you need divide "+ a + " by: "+a/b;
        Assert.assertEquals("To get 0.1 you need divide 12 by: 120.0",testBiFunction.apply(12,.1));

//        takes two the same parameters and return value of the same type
        BinaryOperator<Integer> testBinaryOperatorSum = Integer::sum;
        BinaryOperator<Integer> testBinaryOperatorMultiply = (a,b)-> a*b;
        Assert.assertEquals(Integer.valueOf(25),testBinaryOperatorSum.apply(12,13));
        Assert.assertEquals(Integer.valueOf(156),testBinaryOperatorMultiply.apply(12,13));

        // takes object and do an operation on it - the object have to be mutable
        Consumer<StringBuilder> reverseStringBuilder = StringBuilder::reverse;
        StringBuilder test = new StringBuilder("12345");
        reverseStringBuilder.accept(test);
        Assert.assertEquals("54321",test.toString());

        // takes no parameter create new object
        Supplier<String> createStringSupplier = () -> "new string";
        Assert.assertEquals("new string",createStringSupplier.get());

//takes one parameter and returns boolean
        Predicate<Integer> lessThanZero = x -> x<0;
        Assert.assertTrue(lessThanZero.test(-12));

    }


    @Test(expected = NullPointerException.class)
    public void testOptional() {
        String nullString = null;
        Optional.of(nullString);

    }

}
