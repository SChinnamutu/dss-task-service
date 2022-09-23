package com.example.dss.exception;

import com.example.dss.dto.BaseResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<BaseResponseDTO> handleException(Exception ex) {
		log.error("Exception is :: " + ex.getLocalizedMessage());
		BaseResponseDTO res = BaseResponseDTO.builder()
												.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
												.status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
												.desc(ex.getLocalizedMessage())
										.build();
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<BaseResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
		BaseResponseDTO res = BaseResponseDTO.builder()
											.code(HttpStatus.BAD_REQUEST.value())
											.status(HttpStatus.BAD_REQUEST.getReasonPhrase())
											.desc(ex.getLocalizedMessage())
										.build();
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	}
}


