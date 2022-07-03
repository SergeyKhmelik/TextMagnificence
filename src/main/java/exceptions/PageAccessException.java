package exceptions;

import org.springframework.expression.AccessException;

public class PageAccessException extends AccessException {

    public PageAccessException(String message) {
        super(message);
    }

}
