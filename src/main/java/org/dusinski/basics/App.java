package org.dusinski.basics;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {

        Stream<String> testStream;
        testStream = Stream.generate(() -> "welcome ").limit(20);
        StringBuilder testStringBuilder = new StringBuilder();
        testStream.forEach(testStringBuilder::append);
        System.out.println(testStringBuilder);

        IntStream intStream = IntStream.generate(() -> 12).limit(10);
        intStream = IntStream.range(10, 120);

        intStream.forEach(System.out::println);
        List<Integer> integerList = IntStream.range(10, 120)
                .boxed()
                .collect(Collectors.toList());

        integerList.filter(el-> el<100).forEach(System.out::println);

//        IntStream streamOfChars = "abcdefgh".chars();
//        streamOfChars.forEach(System.out::println);

//        Random random = new Random();
//        DoubleStream doubleStream = random.doubles().limit(5);
//        doubleStream.forEach(System.out::println);

    }
}
