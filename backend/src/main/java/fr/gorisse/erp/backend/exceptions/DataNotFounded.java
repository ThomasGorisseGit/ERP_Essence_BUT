package fr.gorisse.erp.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data not founded")

public class DataNotFounded extends NoSuchElementException {
    public DataNotFounded(String message){
        super(message);
    }
}
