package simple.proxy.config.v2_dynamicproxy.handler;

import lombok.extern.slf4j.Slf4j;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@Slf4j
public class LogTraceBasicHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace trace;

    public LogTraceBasicHandler(Object target, LogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        try {

            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = trace.begin(message);

            Object result = method.invoke(proxy, args);
            trace.end(status);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
