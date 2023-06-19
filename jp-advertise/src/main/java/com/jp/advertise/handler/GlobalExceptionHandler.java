package com.jp.advertise.handler;

import com.jp.common.base.CommonResponse;
import com.jp.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理器
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常捕获
     *
     * @param request 请求信息
     * @param e       异常
     * @return 错误信息
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity defaultErrorHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(500).body(CommonResponse.fail(5000, "发生了内部错误"));
    }


    /**
     * 业务异常 Handler，用于捕获全局业务异常
     *
     * @param e
     * @return 包装后的错误信息
     */
    @ExceptionHandler(BizException.class)
    public ResponseEntity bizExceptionHandler(BizException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(CommonResponse.fail(e.getErrorCode(), e.getMessage()));
    }

    /**
     * RequestParam 参数校验 异常Handler，用于捕获参数校验错误
     *
     * @return 包装后的错误信息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity bizExceptionHandler(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        Set<ConstraintViolation<?>> constraintViolationSet = e.getConstraintViolations();
        List<String> errorMessages = new ArrayList<>(constraintViolationSet.size());
        for (ConstraintViolation error : constraintViolationSet) {
            errorMessages.add(error.getMessage());
        }
        return ResponseEntity.status(400).body(CommonResponse.fail(4000, String.join(",", errorMessages)));
    }


    /**
     * 方法参数验证错误异常 Handler，用于 application/json 方式提交数据
     *
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        return getValidateFailResponseEntity(result);
    }


    /**
     * 方法参数验证错误异常 Handler，用于 get 或者 form 提交时参数绑定
     *
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity bindExceptionHandler(BindException e) {
        BindingResult result = e.getBindingResult();
        return getValidateFailResponseEntity(result);
    }

    /**
     * application/json post 请求无请求体异常 Handler
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return ResponseEntity.status(400).body(CommonResponse.fail(4000, "请求体不存在（Required request body is missing）"));
    }

    /**
     * 参数缺失请求异常 Handler
     *
     * @param e
     * @return 400状态
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity methodArgumentNotValidExceptionHandler(MissingServletRequestParameterException e) {
        String message = "请求参数 '" + e.getParameterName() + "' 不存在";
        return ResponseEntity.status(400).body(CommonResponse.fail(4000, message));
    }

    /**
     * 不支持的 Method
     *
     * @param e
     * @return 405状态
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(405).body(CommonResponse.fail(4050, "请求方法 '" + e.getMethod() + "' 不支持"));
    }


    /**
     * 自定义错误页面
     *
     * @return 返回自定义 WebServerFactory 配置
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (factory -> {
            // 自定义404页面
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            // 添加404页面
            factory.addErrorPages(error404Page);
            // 自定义500页面
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
            // 添加500页面
            factory.addErrorPages(error500Page);
        });
    }

    /**
     * 获取参数验证异常返回数据
     *
     * @param result 验证异常对象，内部包含异常相关信息
     * @return 验证异常返回数据
     */
    private ResponseEntity getValidateFailResponseEntity(BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMessages = new ArrayList<>(fieldErrors.size());
        for (FieldError error : fieldErrors) {
            errorMessages.add(error.getField() + " " + error.getDefaultMessage());
        }
        return ResponseEntity.status(400).body(CommonResponse.fail(4000, String.join(",", errorMessages)));
    }
}