import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description(""" Sends a fraud message when fraud took place """)

    label "notification_fraud_message"

    input {
        triggeredBy('sendNotification()')
        assertThat('assert_something()')
        // messageFrom('channelname')
        // we can have only sssert that something happenned without any output message
    }

    outputMessage {
        sentTo 'notification'
        body([
                "code"         : "0000",
                "participantId": "1111110000",
                "date"         : $(consumer("2018-11-15"), producer(anyDate()))
        ])
    }
}
