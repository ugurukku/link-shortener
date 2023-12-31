package com.ugurukku.linkshortener.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.SqlReturnType;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralResponse<T> {

    int code;
    String message;
    @JsonInclude(NON_NULL)
    T data;

    public GeneralResponse(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static GeneralResponse SUCCESS(String message) {
        return new GeneralResponse<>(200, message);
    }

    public static GeneralResponse SUCCESS() {
        return new GeneralResponse<>(200, "SUCCESS");
    }

    public static GeneralResponse CREATED() {
        return new GeneralResponse<>(201, "SUCCESSFULLY CREATED");
    }

    public static GeneralResponse FORBIDDEN() {
        return new GeneralResponse<>(403, "FORBIDDEN");
    }

}
