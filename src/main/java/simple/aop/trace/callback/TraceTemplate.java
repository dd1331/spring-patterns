package simple.aop.trace.callback;

import simple.aop.trace.TraceStatus;
import simple.aop.trace.logtrace.LogTrace;

public class TraceTemplate {

    private final LogTrace trace;


    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            T result = callback.call();

            trace.end(status);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
