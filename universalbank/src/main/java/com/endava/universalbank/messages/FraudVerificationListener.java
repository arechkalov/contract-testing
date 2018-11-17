package com.endava.universalbank.messages;

import com.endava.universalbank.dto.Participant;
import com.endava.universalbank.dto.fraud.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class FraudVerificationListener {

    AtomicInteger fraudCounter = new AtomicInteger();

    @StreamListener(Sink.INPUT)
    public void listen(Notification notification) {
        if (notification.getCode().equals("0000")) {
            log.info("Received fraud at: {} from participant: {}", notification.getDate(), notification.getParticipantId());
            fraudCounter.incrementAndGet();
            //update something in database
        }
    }
}
