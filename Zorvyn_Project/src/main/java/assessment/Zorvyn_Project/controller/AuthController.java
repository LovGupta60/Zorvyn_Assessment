package assessment.Zorvyn_Project.controller;

import assessment.Zorvyn_Project.dto.LoginDTO;
import assessment.Zorvyn_Project.dto.SignUpDTO;
import assessment.Zorvyn_Project.dto.UserResponseDTO;
import assessment.Zorvyn_Project.entity.User;
import assessment.Zorvyn_Project.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignUpDTO request) {
        return ResponseEntity.status(201).body(
                service.signup(
                        request.getUsername(),
                        request.getPassword(),
                        request.getRole()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDTO request) {

        String token = service.login(
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PutMapping("/update-role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateRole(
            @RequestParam Long id,
            @RequestParam String role) {

        return ResponseEntity.ok(service.updateRole(id, role));
    }
    @GetMapping("/details")
    public ResponseEntity<UserResponseDTO> getUserId(
            @RequestParam String username,
            @RequestParam String role) {

        User user = service.getUser(username, role);

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setRole(String.valueOf(user.getRole()));
        dto.setUsername(user.getUsername());
        dto.setActive(user.isActive());


        return ResponseEntity.ok(dto);
    }
    @PutMapping("/toggle-status")
    public ResponseEntity<String> toggleStatus(@RequestParam Long id) {
        return ResponseEntity.ok(service.toggleUserStatus(id));
    }
}