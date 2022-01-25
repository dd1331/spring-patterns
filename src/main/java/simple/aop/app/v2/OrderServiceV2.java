package simple.aop.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.aop.trace.TraceId;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.simple.SimpleTraceV2;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final SimpleTraceV2 simpleTrace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = simpleTrace.beginSync(traceId, "OrderService.orderItem()");

            orderRepository.save(status.getTraceId(), itemId);
            simpleTrace.end(status);

        } catch (Exception e) {
            simpleTrace.exception(status, e);
            throw e;
        }
    }
}
