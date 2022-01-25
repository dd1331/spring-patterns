package simple.aop.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.simple.SimpleTraceV1;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final SimpleTraceV1 simpleTraceV1;
    TraceStatus status;

    public void orderItem(String itemId) {
        status = simpleTraceV1.begin("OrderService.orderItem()");
        try {
            orderRepository.save(itemId);
            simpleTraceV1.end(status);

        } catch (Exception e) {
            simpleTraceV1.exception(status, e);
            throw e;
        }
    }
}
