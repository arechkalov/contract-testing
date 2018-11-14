import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description(""" Sends a fraud message when fraud took place """)

    label "rejected_message"

    input {
        triggeredBy('participantIsFraud()')
        // messageFrom('channelname')
        // assertThat('assert_something()')
        // we can have only sssert that something happenned without any output message
    }

    outputMessage {
        sentTo 'validation'
        body(
                "participantId": "1234567891",
                "isFraud": true
        )
    }
}
