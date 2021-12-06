package org.seckill.exception.handler;

import org.seckill.base.BaseResponse;
import org.seckill.base.StatusCode;
import org.seckill.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 功能描述: 业务异常
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     */
    @ExceptionHandler(BizException.class)
    public ResponseEntity<ApiError> handleBizException(BizException bizException) {
        logger.error("业务异常: {}", bizException.getStatusCode(), bizException);
        return new ResponseEntity<>(ApiError.error(bizException.getStatusCode()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 功能描述: 业务异常
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<StatusCode> handleException() {
        return BaseResponse.error(StatusCode.EXCEPTION);
    }
}
