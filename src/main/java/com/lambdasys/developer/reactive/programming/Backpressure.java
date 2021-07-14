package com.lambdasys.developer.reactive.programming;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.stream.IntStream;

public class Backpressure {

    public static void main(String... args) throws InterruptedException {

        Flowable.create(
                Backpressure::emit,
                BackpressureStrategy.BUFFER
                //BackpressureStrategy.DROP
                //BackpressureStrategy.ERROR
                //BackpressureStrategy.LATEST
                //BackpressureStrategy.MISSING
        )
                .observeOn(Schedulers.computation(), true , 2 )
                .subscribe(Backpressure::process);

        sleep(10000);
    }

    public static void process(Integer number){
        System.out.printf("Processing %s \n",number);
        // consume in one second
        sleep(1000);
    }

    public static void emit(final FlowableEmitter<Integer> emitter) {
        IntStream.rangeClosed(1,10)
                .forEach( n -> {
                    System.out.printf("Emitting number %s \n",n);
                    emitter.onNext(n);
                    //produce in half of one second
                    sleep(500);
                });
        emitter.onComplete();
    }

    public static void sleep(long millis){
        try{
            Thread.sleep(millis);
        }catch(InterruptedException ex){
            // DO NOTHING
        }
    }

}
