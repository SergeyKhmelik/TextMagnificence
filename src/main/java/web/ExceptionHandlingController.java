package web;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoTimeoutException;
import exceptions.NoSuchDataCouplingException;
import exceptions.NoSuchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import web.data_utils.ResponseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koloturka on 06.08.15.
 */

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(MongoTimeoutException.class)
    public @ResponseBody ResponseError<MongoTimeoutException> handleMongoTimeoutException(MongoTimeoutException ex) {
        /*
        MyExceptionClass exception = new MyExceptionClass();
        exception.setUserMessage("Oups! Server is temporary unavailable.");
        exception.setDeveloperMessage(ex.getMessage());
        exception.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseError<MyExceptionClass>(exception);
        */
        return new ResponseError<MongoTimeoutException>(ex, "Oups! Server is temporary unavailable.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ResponseError<List<FieldError>> handleValidationException(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        errors = ex.getBindingResult().getFieldErrors();
        return new ResponseError<List<FieldError>>(errors, "Object fields are incorrect.");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public @ResponseBody ResponseError<DuplicateKeyException> handleDuplicateKeyInsert(DuplicateKeyException ex){
        /*MyExceptionClass exception = new MyExceptionClass();
        exception.setType(ex.getClass().getName());
        return ex.getMessage();
        */
        return new ResponseError<DuplicateKeyException>(ex, "Object with this id/name already exists.");
    }

    @ExceptionHandler(NoSuchDataException.class)
    public @ResponseBody ResponseError<NoSuchDataException> handleNoSuchDataSearch(NoSuchDataException ex){
        return new ResponseError<NoSuchDataException>(ex, ex.getMessage());
    }

    @ExceptionHandler(NoSuchDataCouplingException.class)
    public @ResponseBody ResponseError<NoSuchDataCouplingException> handleNoSuchDataCouplingSearch(NoSuchDataCouplingException ex){
        return new ResponseError<NoSuchDataCouplingException>(ex, ex.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public @ResponseBody ResponseError<InvalidFormatException> handleInvalidFormatException(InvalidFormatException ex){
        return new ResponseError<InvalidFormatException>(ex, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseError<Exception> handleUnsupportableException(Exception ex){
        return new ResponseError<Exception>(ex, "General exception handler:" +  ex.getMessage());
    }

    @ExceptionHandler(Error.class)
    public @ResponseBody ResponseError<Error> handleUnsupportableError(Error er){
        return new ResponseError<Error>(er, "General error handler:" +  er.getMessage());
    }

}
