package com.endava.fraud.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    public AtomicInteger counter = new AtomicInteger();
    private final Source source;

    public void sendNotification(String code, String participantId) {
        source.output().send(MessageBuilder.withPayload(new Notification(code, participantId,  LocalDate.now())).build());
        log.info("Message to the Bank: {} was sent...", participantId.substring(0, 6));
        counter.incrementAndGet();
    }
}
