package fr.gorisse.erp.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data insertion is incorrect")

public class DataIncorrect extends NoSuchElementException {
    public DataIncorrect(String message){
        super(message);
    }
}
