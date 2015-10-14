package exceptions;

import org.springframework.expression.AccessException;

/**
 * Created by Koloturka on 06.10.2015.
 */
public class PageAccessException extends AccessException {

    public PageAccessException(String message) {
        super(message);
    }

}
