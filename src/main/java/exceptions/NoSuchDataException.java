package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchDataException extends NoSuchElementException {

	private Class missingDataClass;
	private Object missingDataId;

	public NoSuchDataException(Class missingDataClass, Object missingDataId) {
		this.missingDataClass = missingDataClass;
		this.missingDataId = missingDataId;
	}

	@Override
	public String getMessage() {
		return "NoSuchDataException: " +
				"(There is no " + missingDataClass.getName() +
				" with id=" + missingDataId + ")";
	}

}
