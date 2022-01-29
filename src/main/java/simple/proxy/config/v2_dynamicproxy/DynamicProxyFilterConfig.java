package simple.proxy.config.v2_dynamicproxy;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simple.aop.trace.logtrace.LogTrace;
import simple.proxy.app.v1.*;
import simple.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import simple.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private final String[] patterns = {"request*", "order*", "save*"};

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(
                OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceFilterHandler(orderRepository, trace, patterns)
        );
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(trace));

        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(
                OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(orderServiceV1, trace, patterns)
        );

        return proxy;
    }

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(trace));

        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(
                OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(orderController, trace, patterns)
        );

        return proxy;
    }

}
