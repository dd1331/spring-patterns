package simple.proxy.config.v2_dynamicproxy.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@Slf4j
public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace trace;
    private final String[] patterns;

    public LogTraceFilterHandler(Object target, LogTrace trace, String[] patterns) {
        this.target = target;
        this.trace = trace;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();

        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {

            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = trace.begin(message);

            Object result = method.invoke(target, args);
            trace.end(status);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
