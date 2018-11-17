package contracts.bcr

import com.endava.bank.ConsumerUtils
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description """
                should mark participant as not fraud when amount is less then limit
            """
    request {
        method PUT()
        url '/fraudcheck'
        body([
                "participant.id": $(value(consumer(regex('[0-9]{10}')), producer('2222220000'))),
                "loanAmount"    : $(ConsumerUtils.loanAmountIsLessThanLimit())
        ])
        headers {
            contentType("application/json")
        }

    }

    response {
        status OK()
        body(
                fraudCheckStatus: "OK"
        )
        headers {
            contentType("application/json")
        }
    }

}
