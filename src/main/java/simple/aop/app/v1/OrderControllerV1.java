package simple.aop.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.simple.SimpleTraceV1;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderServiceV0;
    private final SimpleTraceV1 simpleTraceV1;

    TraceStatus status;
//    TraceStatus status = null;
    @GetMapping("v1/request")
    public String request(String itemId) {
        status = simpleTraceV1.begin("OrderController.request()");
        try {
            orderServiceV0.orderItem(itemId);
            simpleTraceV1.end(status);
            return "ok";

        } catch (Exception e) {
            simpleTraceV1.exception(status, e);
            throw e;
        }
    }
}
