package simple.aop.trace.callback;

public interface TraceCallback<T> {
    T call();
}
