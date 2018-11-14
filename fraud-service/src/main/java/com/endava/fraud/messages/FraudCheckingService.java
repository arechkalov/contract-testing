package com.endava.fraud.messages;

import com.endava.fraud.model.FraudCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static com.endava.fraud.FraudDetectionController.MAX_AMOUNT;

@Service
@RequiredArgsConstructor
public class FraudCheckingService {

    private final Source source;

    public boolean participantIsFraud(FraudCheck fraudCheck) {
        boolean isParticipantFraud = MAX_AMOUNT.compareTo(fraudCheck.getLoanAmount()) < 0;

        source.output().send(MessageBuilder.withPayload(new Verification(fraudCheck.getParticipantId(),
                        isParticipantFraud)).build());
        return isParticipantFraud;
    }

    public static class Verification {

        public String participantId;

        public boolean isFraud;

        public Verification(String participantId, boolean isFraud) {
            this.participantId = participantId;
            this.isFraud = isFraud;
        }
    }
}
