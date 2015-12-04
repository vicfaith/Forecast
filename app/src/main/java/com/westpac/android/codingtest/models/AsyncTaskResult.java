package com.westpac.android.codingtest.models;

/**
 * Created by dkang on 4/12/15.
 */
public class AsyncTaskResult<T> {
    private T result;
    private Exception error;

    public T getResult() {
        return result;
    }

    public Exception getError() {
        return error;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setError(Exception error) {
        this.error = error;
    }
}
