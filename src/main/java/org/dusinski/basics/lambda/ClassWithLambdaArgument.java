package org.dusinski.basics.lambda;

public class ClassWithLambdaArgument {

    public static String doAction(BlockLambdaExpression inputExpression, String inputString){
        return inputExpression.modifyString(inputString);
    }

    public static boolean checkPredicate(SingleLambdaExpression inputExpression, int inputValue){
        return  inputExpression.doTest(inputValue);
    }

}
