package simple.proxy.config.v1_proxy.concrete_proxy;

import lombok.extern.java.Log;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.logtrace.LogTrace;
import simple.proxy.app.v2.OrderRepositoryV2;
import simple.proxy.app.v2.OrderServiceV2;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;
    private final LogTrace trace;

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

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
