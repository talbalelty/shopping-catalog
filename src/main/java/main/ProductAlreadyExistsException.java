package main;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 7058005547279585648L;

	public ProductAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ProductAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ProductAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
