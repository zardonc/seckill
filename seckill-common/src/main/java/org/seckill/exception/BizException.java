package org.seckill.exception;

import org.seckill.base.StatusCode;

/**
 * 业务异常
 */
public class BizException extends RuntimeException {

    private StatusCode statusCode;

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
