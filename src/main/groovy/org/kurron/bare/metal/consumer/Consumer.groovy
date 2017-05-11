package org.kurron.bare.metal.consumer

import groovy.util.logging.Slf4j
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener
import org.springframework.beans.factory.annotation.Autowired

import java.time.Duration
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

/**
 * Processes messages.
 */
@Slf4j
class Consumer implements MessageListener {

    def counter = new AtomicInteger( 0 )
    def start = new AtomicLong( 0 )
    def now = new AtomicLong( 0 )
    def duration = new AtomicLong( 0 )

    @Autowired
    ApplicationProperties configuration

    @Override
    void onMessage( Message message ) {
        // only store the first timestamp
        start.compareAndSet( 0, System.currentTimeMillis() )
        now.set( System.currentTimeMillis() )
        def currentValue = counter.incrementAndGet()
        def durationMillis = duration.addAndGet( now.get() - start.get() )
        def durationISO = Duration.ofMillis( durationMillis )
        0 == currentValue % configuration.modvalue ? log.info( '{} messages has taken {} milliseconds to process', currentValue, durationMillis ) : ''
    }
}
