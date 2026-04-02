package assessment.Zorvyn_Project.controller;


import assessment.Zorvyn_Project.dto.LoginRequest;
import assessment.Zorvyn_Project.dto.SignupRequest;
import assessment.Zorvyn_Project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {

        return service.signup(
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return service.login(
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );
    }
}