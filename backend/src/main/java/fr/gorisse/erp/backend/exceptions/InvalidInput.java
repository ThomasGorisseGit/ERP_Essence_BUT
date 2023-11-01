package fr.gorisse.erp.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Invalid input")

public class InvalidInput extends RuntimeException {
    public InvalidInput(String message){
        super(message);
    }
}
