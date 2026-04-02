package assessment.Zorvyn_Project.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(ResourceNotFoundException ex) {

        Map<String, String> map = new HashMap<>();
        map.put("error", ex.getMessage());

        return map;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRuntime(RuntimeException ex) {

        Map<String, String> map = new HashMap<>();
        map.put("error", ex.getMessage());

        return map;
    }
}
