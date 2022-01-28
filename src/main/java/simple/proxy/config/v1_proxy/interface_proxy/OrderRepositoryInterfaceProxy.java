package simple.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.logtrace.LogTrace;
import simple.proxy.app.v1.OrderRepositoryV1;


@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {


    private final OrderRepositoryV1 target;
    private final LogTrace trace;


    @Override
    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");

            target.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
