package com.example.shortenurl.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("===============>> handlerMethodArgumentNotValidException", e);
        return getErrorResponseByBindingResult(e.getBindingResult(), "invalid argument!!");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PathRequiredException.class)
    public ErrorResponse handlerPathRequiredException(MethodArgumentNotValidException e) {
        log.error("===============>> handlerPathRequiredException", e);
        return getErrorResponseByBindingResult(e.getBindingResult(), "invalid argument!!");
    }

    private ErrorResponse getErrorResponseByBindingResult(BindingResult bindingResult, String defaultMessage) {
        List<String> details = Optional.ofNullable(bindingResult.getAllErrors())
                .orElse(Collections.emptyList())
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toUnmodifiableList());

        return ErrorResponse.of(CollectionUtils.isEmpty(details) ? defaultMessage : details.get(0), details);
    }

}
