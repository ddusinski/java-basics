package org.dusinski.basics.lambda;

public interface GenericLambdaExpression<E> {
    boolean doTest(E firstParameter, E secondParameter);
}
