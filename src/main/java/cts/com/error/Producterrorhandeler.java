package cts.com.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Producterrorhandeler {

	@ExceptionHandler(value = ProductupdateException.class)
	protected Errorinfo myException(Exception e, HttpServletRequest request) {
		String msg = e.getMessage();
		String uri = request.getRequestURI();
		return new Errorinfo(uri, msg);
	}
}
