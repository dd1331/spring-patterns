package simple.proxy.config.v1_proxy.concrete_proxy;

import simple.aop.trace.TraceStatus;
import simple.aop.trace.logtrace.LogTrace;
import simple.proxy.app.v2.OrderControllerV2;
import simple.proxy.app.v2.OrderServiceV2;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

    LogTrace trace;
    OrderControllerV2 target;

    public OrderControllerConcreteProxy(OrderControllerV2 target, LogTrace trace) {

        super(null);
        this.trace = trace;
        this.target = target;
    }

    @Override
    public String request(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");

            String result = target.request(itemId);
            trace.end(status);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
