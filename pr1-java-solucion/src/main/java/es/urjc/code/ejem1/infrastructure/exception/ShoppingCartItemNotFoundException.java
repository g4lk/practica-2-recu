package es.urjc.code.ejem1.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ShoppingCartItemNotFoundException extends RuntimeException {

	private static final Long serialVersionUID = -5596141541624573125L;

	public ShoppingCartItemNotFoundException(String message) {
		super(message);
	}
}
