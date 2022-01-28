package simple.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.logtrace.LogTrace;
import simple.proxy.app.v1.OrderControllerV1;


@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

    private final OrderControllerV1 target;
    private final LogTrace trace;

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
