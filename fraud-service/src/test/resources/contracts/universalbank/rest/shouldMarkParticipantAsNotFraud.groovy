package contracts.universalbank.rest

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description """
                should mark participant as not fraud
            """
    request {
        method 'PUT'
        url '/fraudcheck'
        body([ // (4)
               "participant.id": $(value(consumer(regex('[0-9]{10}')), producer('1111110000'))),
               loanAmount      : 200.11
        ])

        headers {
            contentType("application/json")
        }

    }

    response {
        status OK()
        body(
                fraudCheckStatus: "OK",
                "rejection.reason": $(consumer(null), producer(execute('assertThatRejectionReasonIsNull($it)')))
        )

        headers {
            contentType("application/json")
        }
    }

}
