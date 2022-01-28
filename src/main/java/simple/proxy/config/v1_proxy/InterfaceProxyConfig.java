package simple.proxy.config.v1_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simple.aop.trace.logtrace.LogTrace;
import simple.proxy.app.v1.*;
import simple.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import simple.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import simple.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;

@Configuration
public class InterfaceProxyConfig {


    @Bean
    public OrderControllerV1 orderController(LogTrace trace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderService(trace));
        return new OrderControllerInterfaceProxy(orderControllerV1, trace);
    }


    @Bean
    public OrderServiceV1 orderService(LogTrace trace) {
        OrderServiceV1Impl orderServiceV1 = new OrderServiceV1Impl(orderRepository(trace));
        return new OrderServiceInterfaceProxy(orderServiceV1, trace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        OrderRepositoryV1Impl orderRepositoryV1 = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepositoryV1, trace);
    }
}
