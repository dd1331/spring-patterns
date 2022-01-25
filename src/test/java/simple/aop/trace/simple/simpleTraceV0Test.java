package simple.aop.trace.simple;

import org.junit.jupiter.api.Test;
import simple.aop.trace.TraceStatus;


class simpleTraceV0Test {
    @Test
    void begin_end() {
        SimpleTraceV1 trace = new SimpleTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        SimpleTraceV1 trace = new SimpleTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}