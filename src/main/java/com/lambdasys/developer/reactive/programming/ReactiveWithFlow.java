package com.lambdasys.developer.reactive.programming;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class ReactiveWithFlow {

    private static Logger log = Logger.getAnonymousLogger();

    public static void main(String... args){

        final var subscriber = new CustomSubscriber();

        try(final var publisher = new SubmissionPublisher<Integer>()){
            publisher.subscribe(subscriber);
            IntStream.range(1,10)
                    .forEach( n -> {
                        log.info(String.format("Emitting %s",n));
                        publisher.submit(n);
                    });
            sleep();
        }

    }

    public static void sleep(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            // DO NOTHING
        }
    }

}

class CustomSubscriber implements Flow.Subscriber<Integer> {

    private final Logger log = Logger.getAnonymousLogger();
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(final Flow.Subscription subscription){
        this.subscription = subscription;
        this.subscription.request(5);
    }

    @Override
    public void onNext(final Integer item){
        log.info(String.format("receiving %s",item));
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        log.info("Done!");
    }

}
