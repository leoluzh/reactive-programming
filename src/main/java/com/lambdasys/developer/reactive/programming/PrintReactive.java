package com.lambdasys.developer.reactive.programming;


import io.reactivex.rxjava3.core.Flowable;

import java.util.concurrent.TimeUnit;

public class PrintReactive {

    public static void main(String... args) throws InterruptedException {

        io.reactivex.rxjava3.core.

        Flowable.interval( 1 , 1 , TimeUnit.SECONDS)
                .map( n -> n * 2 )
                .subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(5);

    }


}
