package module;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class ValidationErrors {

    private final List<ValidationError> errors = new ArrayList<>();

    public void addFieldError(FieldError fieldError) {
        List<String> args = Stream.of(Objects.requireNonNull(fieldError.getArguments()))
                .filter(arg -> !(arg instanceof DefaultMessageSourceResolvable))
                .map(String::valueOf)
                .collect(Collectors.toList());

        errors.add(new ValidationError(Objects.requireNonNull(fieldError.getCodes())[0], args));
    }

    @Getter
    @AllArgsConstructor
    @RequiredArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ValidationError {
        @NonNull
        private String code;
        private List<String> arguments;
    }
}
