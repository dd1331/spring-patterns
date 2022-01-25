package simple.aop.trace.simple;

import org.junit.jupiter.api.Test;
import simple.aop.trace.TraceStatus;


class simpleTraceV2Test {
    @Test
    void begin_end() {
        SimpleTraceV2 trace = new SimpleTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        SimpleTraceV2 trace = new SimpleTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());

    }
}