package contracts.bcr

import com.endava.bank.ConsumerUtils
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description """
                should mark participant as fraud when amount is higher than limit
    """
    request { // (1)
        method PUT() // (2)
        url '/fraudcheck' // (3)
        body([ // (4)
               "participant.id": $(value(consumer(regex('[0-9]{10}')), producer('2222220000'))),
               loanAmount      : $(ConsumerUtils.loanAmountIsBiggerThanLimit())
        ])
        headers { // (5)
            contentType('application/json')
        }

    }

    response { // (6)
        status OK() // (7)
        body([ // (8)
               fraudCheckStatus: "FRAUD"
        ])
        headers { // (9)
            contentType('application/json')
        }
    }
}

/*
From the Consumer perspective, when shooting a request in the integration test:

(1) - If the consumer sends a request
(2) - With the "PUT" method
(3) - to the URL "/fraudcheck"
(4) - with the JSON body that
 * has a field `clientId` that matches a regular expression `[0-9]{10}`
 * has a field `loanAmount` that is equal to `99999`
(5) - with header `Content-Type` equal to `application/json`
(6) - then the response will be sent with
(7) - status equal `200`
(8) - and JSON body equal to
 { "fraudCheckStatus": "FRAUD", "rejectionReason": "Amount too high" }
(9) - with header `Content-Type` equal to `application/json`

From the Producer perspective, in the autogenerated producer-side test:

(1) - A request will be sent to the producer
(2) - With the "PUT" method
(3) - to the URL "/fraudcheck"
(4) - with the JSON body that
 * has a field `clientId` that will have a generated value that matches a regular expression `[0-9]{10}`
 * has a field `loanAmount` that is equal to `99999`
(5) - with header `Content-Type` equal to `application/json`
(6) - then the test will assert if the response has been sent with
(7) - status equal `200`
(8) - and JSON body equal to
 { "fraudCheckStatus": "FRAUD", "rejectionReason": "Amount too high" }
(9) - with header `Content-Type` matching `application/json.*`
 */