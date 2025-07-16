package com.mic.product.commons.exception;

import com.mic.product.infrastructure.domain.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


        // Maneja la excepción personalizada ResourceNotFoundException (HTTP 404 Not Found)
        @ExceptionHandler(ResourceNotFoundException.class) // ¡Cambiar aquí!
        public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO();
            errorResponse.setTimestamp(OffsetDateTime.now());
            errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
            errorResponse.setMessage(ex.getMessage() != null ? ex.getMessage() : "Resource not found"); // Usa el mensaje de tu excepción
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        // Maneja las excepciones de validación (@Valid) (HTTP 400 Bad Request)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));

            ErrorResponseDTO errorResponse = new ErrorResponseDTO();
            errorResponse.setTimestamp(OffsetDateTime.now());
            errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
            errorResponse.setMessage("Error de validación en la solicitud.");
            // Considera añadir un campo 'details' o 'fieldErrors' a ErrorResponseDTO para enviar el mapa 'errors'
            // Por ahora, para mostrar, podemos unir los mensajes
            String fieldErrorsMessage = errors.entrySet().stream()
                    .map(entry -> entry.getKey() + ": " + entry.getValue())
                    .collect(Collectors.joining("; "));
            errorResponse.setMessage("Violation fields : " + fieldErrorsMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ProductBadRequestException.class)
        public ResponseEntity<ErrorResponseDTO> handleProductBadRequest(ProductBadRequestException ex) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO();
            errorResponse.setTimestamp(OffsetDateTime.now());
            errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
            errorResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }


        // Manejador genérico para cualquier otra excepción no capturada (HTTP 500 Internal Server Error)
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex, WebRequest request) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO();
            errorResponse.setTimestamp(OffsetDateTime.now());
            errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            errorResponse.setMessage("Unexpected error o server: " + ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    /**
     * Maneja la excepción ProductAlreadyExistsException (HTTP 409 Conflict).
     * Esto ocurre cuando intentamos crear un recurso que ya existe con un identificador único.
     */
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductAlreadyExistsException(ProductAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setTimestamp(OffsetDateTime.now());
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
