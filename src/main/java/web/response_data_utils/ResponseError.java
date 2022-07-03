package web.response_data_utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is needed to make all responding
 * errors match a single error pattern, which is:
 * {
 *    type: "error class name here",
 *    userMessage: "user message here",
 *    errors:
 *    		[
 *    			... here are errors with their stack trace
 *    		]
 * }
 */
public class ResponseError<T> {

	private String type;
	private String userMessage;
	private List<T> errors;

	public ResponseError(T error, String userMessage) {
		if (error instanceof List) {
			this.errors = (List<T>) error;
		} else {
			this.errors = new ArrayList<T>();
			errors.add(error);
		}
			this.type = errors.get(0).getClass().getName();
			this.userMessage = userMessage;
	}

	public List<T> getErrors() {
		return errors;
	}

	public void setErrors(List<T> errors) {
		this.errors = errors;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

}
