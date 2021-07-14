package com.lambdasys.developer.reactive.programming;

import io.reactivex.rxjava3.core.Flowable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PrintReactiveErrorFlow {

    public static void main(String... args) throws InterruptedException {

        Flowable.interval(1 , 1 , TimeUnit.SECONDS )
                .map(PrintReactiveErrorFlow::transform)
                .subscribe(
                        PrintReactiveErrorFlow::process,
                        PrintReactiveErrorFlow::dealWithError);

        TimeUnit.SECONDS.sleep(10);

    }

    public static void dealWithError(final Throwable throwable){
        throwable.printStackTrace();
    }

    public static void process(final Long number) {
        System.out.printf("Received number %s \n",number);
    }

    public static Long transform(final Long number){
        if(new Random().nextDouble() < 0.3 ){
            throw new RuntimeException("Ops! Generate a random error!");
        }
        return number * 2 ;
    }

}
