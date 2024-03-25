package cl.ckrchile.responsibleservice.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParametherNullorInvalidExcepcion extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ParametherNullorInvalidExcepcion(String message) {
        super(message);
    }

}