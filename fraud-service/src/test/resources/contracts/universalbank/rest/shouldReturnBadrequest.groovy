package contracts.bcr

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description """
                should return bad request when participant id is null
            """
    request {
        method 'PUT'
        url '/fraudcheck'
        body([
                "participant.id": null,
                "loanAmount"    : 200.11
        ])
        headers {
            contentType("application/json")
        }

    }

    response {
        status BAD_REQUEST()
    }
}
