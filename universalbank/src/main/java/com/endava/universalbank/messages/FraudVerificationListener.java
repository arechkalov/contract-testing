package com.endava.universalbank.messages;

import com.endava.universalbank.dto.fraud.Verification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class FraudVerificationListener {

    private static final Logger logger = LoggerFactory.getLogger(FraudVerificationListener.class);

    AtomicInteger fraudCounter = new AtomicInteger();
    AtomicInteger notFraudCounter = new AtomicInteger();

    @StreamListener(Sink.INPUT)
    public void listen(Verification verification) {
        logger.info("Received fraud status");
        if (verification.isFraud) {
            fraudCounter.incrementAndGet();
            logger.info("Participant: {} is fraud", verification.participantId);
        } else {
            notFraudCounter.incrementAndGet();
            logger.info("Participant: {} is not a fraud", verification.participantId);
        }
    }
}
