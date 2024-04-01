package servlet;

import module.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ValidationErrors handleValidationError(MethodArgumentNotValidException exception) {
        List<FieldError> errors;
        errors = exception.getBindingResult().getFieldErrors();
        ValidationErrors result = new ValidationErrors();
        errors.forEach(result::addFieldError);
        return result;
    }
}
