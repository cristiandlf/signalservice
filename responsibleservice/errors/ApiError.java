package cl.ckrchile.responsibleservice.errors;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
@Builder
public class ApiError {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private String details;

}