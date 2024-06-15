package com.mall.common.exception;

import com.mall.common.Const;
import com.mall.common.model.FailResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Value("local")
    private String activeProfile;

    // 사용자 validation 에 대한 예외처리 : Pathvariable 대응
    @ExceptionHandler(ConstraintViolationException.class)
    public FailResponse constraintViolationExceptionHandler(ConstraintViolationException e) {
        e.printStackTrace();

        return new FailResponse(Const.ResponseCode.INPUT_CHECK_ERROR.getCode(), e.getMessage());
    }

    // 사용자 validation 에 대한 예외처리 : Dto 대응
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FailResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();

        // 여러개의 Valid 오류가 올 수 있지만 하나씩 해결해야 하기 때문에 첫번째 항목만 Return
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new FailResponse(Const.ResponseCode.INPUT_CHECK_ERROR.getCode(), message);
    }

    @ExceptionHandler(BindException.class)
    public FailResponse bindExceptionHandler(BindException e) {
        e.printStackTrace();

        // 여러개의 Valid 오류가 올 수 있지만 하나씩 해결해야 하기 때문에 첫번째 항목만 Return
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new FailResponse(Const.ResponseCode.INPUT_CHECK_ERROR.getCode(), message);
    }

    @ExceptionHandler(Exception.class)
    public FailResponse exceptionHandler(Exception e) {
        e.printStackTrace();
        return new FailResponse(Const.ResponseCode.INTERNAL_SERVER_ERROR.getCode(), Const.ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public FailResponse runtimeErrorHandler(RuntimeException e) {
        e.printStackTrace();
        FailResponse response = null;

        switch (e.getClass().getName()) {
            case "com.whataulsan.common.exception.AuthException":
                break;
            case "com.whataulsan.common.exception.InputCheckException":
                response = new FailResponse(Const.ResponseCode.INPUT_CHECK_ERROR.getCode(), e.getMessage());
                break;
            default:
                if ("prd".equals(activeProfile)) {
                    response = new FailResponse(Const.ResponseCode.INTERNAL_SERVER_ERROR.getCode(), Const.ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
                } else {
                    response = new FailResponse(Const.ResponseCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
                }
        }

        return response;
    }
}
