package rw.ac.rca.termOneExam.utils.Exceptions;

public class BadRequestExceptions extends RuntimeException {
    public BadRequestExceptions(String message){
        super(message);
    }

    public BadRequestExceptions(String message, Throwable cause){
        super(message,cause);
    }
}
