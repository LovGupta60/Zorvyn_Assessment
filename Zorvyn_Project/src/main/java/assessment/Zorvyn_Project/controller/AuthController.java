package assessment.Zorvyn_Project.controller;

import assessment.Zorvyn_Project.dto.LoginRequest;
import assessment.Zorvyn_Project.dto.SignupRequest;
import assessment.Zorvyn_Project.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest request) {

        return ResponseEntity.status(201).body(
                service.signup(
                        request.getUsername(),
                        request.getPassword(),
                        request.getRole()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {

        String token = service.login(
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PutMapping("/update-role")
    public ResponseEntity<String> updateRole(@RequestParam String username,
                                             @RequestParam String role) {

        return ResponseEntity.ok(service.updateRole(username, role));
    }

    @PutMapping("/toggle-status")
    public ResponseEntity<String> toggleStatus(@RequestParam String username,
                                               @RequestParam String role) {

        return ResponseEntity.ok(service.toggleUserStatus(username, role));
    }
}