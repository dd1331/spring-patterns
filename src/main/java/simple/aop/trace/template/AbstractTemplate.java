package simple.aop.trace.template;

import lombok.extern.slf4j.Slf4j;
import simple.aop.trace.TraceStatus;
import simple.aop.trace.logtrace.LogTrace;

@Slf4j
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    protected AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }


    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            T result = call();

            trace.end(status);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    protected abstract T call();
}
