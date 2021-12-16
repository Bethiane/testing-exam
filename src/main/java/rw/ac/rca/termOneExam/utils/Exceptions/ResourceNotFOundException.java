package rw.ac.rca.termOneExam.utils.Exceptions;

import java.util.UUID;

public class ResourceNotFOundException extends RuntimeException{
    public ResourceNotFOundException(String message) {
        super(message);
    }
    public ResourceNotFOundException(String resourceName, String fieldName, UUID fieldValue){
        super(String.format("%s with %s['%s'] " + "is not found", resourceName,fieldName,fieldValue));
    }
}
