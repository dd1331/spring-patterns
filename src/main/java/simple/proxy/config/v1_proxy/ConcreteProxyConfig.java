package simple.proxy.config.v1_proxy;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simple.aop.trace.logtrace.LogTrace;
import simple.proxy.app.v2.OrderControllerV2;
import simple.proxy.app.v2.OrderRepositoryV2;
import simple.proxy.app.v2.OrderServiceV2;
import simple.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import simple.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import simple.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    OrderRepositoryV2 orderRepositoryV2(LogTrace trace) {
        OrderRepositoryV2 orderRepository = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(orderRepository, trace);
    }

    @Bean
    OrderServiceV2 orderServiceV2(LogTrace trace) {
        OrderServiceV2 orderService = new OrderServiceV2(orderRepositoryV2(trace));
        return new OrderServiceConcreteProxy(orderService, trace);
    }

    @Bean
    OrderControllerV2 orderControllerV2(LogTrace trace) {
        OrderControllerV2 orderController = new OrderControllerV2(orderServiceV2(trace));
        return new OrderControllerConcreteProxy(orderController, trace);
    }
}
