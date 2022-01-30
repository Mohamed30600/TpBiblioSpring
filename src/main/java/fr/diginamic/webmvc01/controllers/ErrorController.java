package fr.diginamic.webmvc01.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ErrorController {

	
	public ErrorController() {}
	
	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String errorGeneralException(Exception e) {
		String message = "Il y a une erreur: "+e.getMessage();
		return message;
	}
	
}


