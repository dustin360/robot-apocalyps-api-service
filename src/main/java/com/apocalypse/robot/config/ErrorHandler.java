package com.apocalypse.robot.config;



import com.apocalypse.robot.dto.GeneralResponse;
import com.apocalypse.robot.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class ErrorHandler {

	@ExceptionHandler(value = { NotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public GeneralResponse notFoundErrorHandler(NotFoundException ex) {
		log.error(ex.getMessage(), ex);
		return new GeneralResponse(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
	}


	// Overide because this message is created elsewhere
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex) {
		log.error(ex.getMessage(), ex);

		log.info("Method Argument not valid throwing....");

		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage())
				.collect(Collectors.toList());

		return new GeneralResponse(HttpStatus.BAD_REQUEST.value(), errorList.get(0));

	}

	@ExceptionHandler(value = { EntityAlreadyExistsException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public GeneralResponse entityAlreadyExistsErrorHandler(
			EntityAlreadyExistsException ex) {
		log.error(ex.getMessage(), ex);
		return new GeneralResponse(HttpStatus.CONFLICT.value(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse illegalArgumentExceptionHandler(IllegalArgumentException ex) {
		log.error(ex.getMessage(), ex);
		return new GeneralResponse(HttpStatus.BAD_REQUEST.value(),
				ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { ConflictException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public GeneralResponse conflictExceptionHandler(ConflictException ex) {
		log.error(ex.getMessage(), ex);
		return new GeneralResponse(HttpStatus.CONFLICT.value(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { BadRequestException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse badRequestExceptionHandler(BadRequestException ex) {
		return new GeneralResponse(HttpStatus.BAD_REQUEST.value(),
				ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		log.error(ex.getMessage(), ex);
		return new GeneralResponse(HttpStatus.BAD_REQUEST.value(),
				ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public GeneralResponse handleGeneralExceptions(Exception ex,
                                                   HttpServletRequest request,
												   HttpServletResponse response) {

		log.error(ex.getMessage(), ex);

		return new GeneralResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unfortunately, the request cannot be processed");
	}

}
