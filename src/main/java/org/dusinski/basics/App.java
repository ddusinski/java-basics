package org.dusinski.basics;


import java.util.function.Consumer;

public class App {
    public static void main(String[] args) {

        Consumer<String> removeSpaces = x -> x.replace(" ", "");

        String test = " t t t t";

        removeSpaces.accept(test);

        System.out.println(test);
    }
}
