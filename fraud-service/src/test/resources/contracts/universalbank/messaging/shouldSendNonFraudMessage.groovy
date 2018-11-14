import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description(""" Sends a positive message when payment was ok """)

    label "accepted_message"

    input {
        triggeredBy('participantIsNotFraud()')
    }

    outputMessage {
        sentTo 'validation'
        body(
                "participantId": "1234567890",
                "isFraud": false
        )
    }
}
