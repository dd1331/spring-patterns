package simple.proxy.config.v1_proxy.interface_proxy;


import lombok.RequiredArgsConstructor;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.logtrace.LogTrace;
import simple.proxy.app.v1.OrderServiceV1;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {

    private final OrderServiceV1 target;
    private final LogTrace trace;


    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");

            target.orderItem(itemId);
            trace.end(status);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
