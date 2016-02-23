package io.techery.janet;

public abstract class ActionAdapter {

    protected Callback callback;

    final <A> void send(A action) {
        try {
            sendInternal(action);
        } catch (JanetException e) {
            this.callback.onFail(action, e);
        }
    }

    abstract protected <T> void sendInternal(T action) throws JanetException;

    abstract <A> void cancel(A action);

    abstract Class getSupportedAnnotationType();

    final void setCallback(Callback callback) {
        this.callback = callback;
    }

    interface Callback {
        void onStart(Object action);
        void onProgress(Object action, int progress);
        void onSuccess(Object action);
        void onFail(Object action, JanetException e);
    }
}
