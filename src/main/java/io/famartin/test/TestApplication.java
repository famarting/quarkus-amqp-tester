package io.famartin.test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;

/**
 * TestApplication
 */
@ApplicationScoped
public class TestApplication {

    @Inject
    @Channel("outputaddr")
    Emitter<String> output;

    void init(@Observes StartupEvent ev) {
        while (true) {
            output.send("Hello world");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Incoming("inputaddr")
    void log(String msg){
        System.out.println("Message received "+msg);
    }
    
}