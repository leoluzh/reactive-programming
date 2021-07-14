package com.lambdasys.developer.reactive.programming;

import java.util.stream.IntStream;

public class PrintStream {

    public static void main(String... args){
        IntStream.range(1,10)
                .map( n -> n * 2 )
                .forEach(System.out::println);
    }

}
