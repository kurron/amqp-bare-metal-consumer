package org.kurron.bare.metal.consumer

import groovy.util.logging.Slf4j
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener

/**
 * Processes messages.
 */
@Slf4j
class Consumer implements MessageListener {
    @Override
    void onMessage( Message message ) {
        log.debug( message.messageProperties.correlationIdString )
    }
}
