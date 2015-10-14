package web.data_utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koloturka
 * @version 18.08.2015 18:41
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
