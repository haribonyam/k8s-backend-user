package com.welab.k8s_backend_user.advice;


import com.welab.k8s_backend_user.common.dto.ApiResponseDto;
import com.welab.k8s_backend_user.common.exception.BadParameter;
import com.welab.k8s_backend_user.common.exception.ClientError;
import com.welab.k8s_backend_user.common.exception.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(value = 1)
@RestControllerAdvice
public class ApiCommonAdvice {
    @ExceptionHandler({BadParameter.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto<String> handleBadParameter(BadParameter e){
        e.printStackTrace();
        return ApiResponseDto.createError(
                e.getErrorCode(),
                e.getErrorMessage()
        );
    }

    @ExceptionHandler({NotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponseDto<String> handleNotFound(NotFound e){
        e.printStackTrace();
        return ApiResponseDto.createError(
                e.getErrorCode(),
                e.getErrorMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ClientError.class})
    public ApiResponseDto<String> handleClientError(ClientError e) {
        e.printStackTrace();
        return ApiResponseDto.createError(
                e.getErrorCode(),
                e.getErrorMessage()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ApiResponseDto<String> handleException(Exception e) {
        e.printStackTrace();
        return ApiResponseDto.createError(
                "ServerError", "서버 에러입니다."
        );
    }
}

