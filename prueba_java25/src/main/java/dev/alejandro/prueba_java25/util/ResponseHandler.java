package dev.alejandro.prueba_java25.util;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    // This class can be used to standardize API responses in the future
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    public static <T> ResponseEntity<Object> generateSuccessResponse(String message, T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        response.put("status", SUCCESS);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<Object> generateErrorResponse(String message, T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        response.put("status", ERROR);
        return ResponseEntity.badRequest().body(response);
    }

    public static <T> ResponseEntity<Object> generateListSuccessResponse(String message, T data, int totalItems, int totalPages) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);
        response.put("status", SUCCESS);
        return ResponseEntity.ok(response);
    }
}
