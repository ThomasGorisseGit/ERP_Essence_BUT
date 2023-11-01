package fr.gorisse.erp.backend.exceptions;

public class InvalidInput extends RuntimeException {
    public InvalidInput(String message){
        super(message);
    }
}
