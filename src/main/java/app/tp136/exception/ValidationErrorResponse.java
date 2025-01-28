package app.tp136.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorResponse {
    private long timestamp;
    private int status;
    private String error;
    private String path;
    private List<String> errors;

    public ValidationErrorResponse(
            long timestamp,
            int status,
            String error,
            String path,
            List<String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        this.errors = errors;
    }
}
