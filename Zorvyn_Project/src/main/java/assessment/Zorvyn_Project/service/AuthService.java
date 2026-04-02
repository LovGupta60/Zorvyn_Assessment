package assessment.Zorvyn_Project.service;


import assessment.Zorvyn_Project.entity.Role;
import assessment.Zorvyn_Project.entity.User;
import assessment.Zorvyn_Project.repository.UserRepository;
import assessment.Zorvyn_Project.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwt;

    public String signup(String username, String password, String role) {

        Role userRole;

        try {
            userRole = Role.valueOf(role.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid role");
        }

        if (repo.existsByUsernameAndRole(username, userRole)) {
            throw new RuntimeException("User already exists with same role");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(userRole);
        user.setActive(true);

        repo.save(user);

        return "User registered successfully";
    }

    public String login(String username, String password, String role) {

        Role userRole;

        try {
            userRole = Role.valueOf(role.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid role");
        }

        User user = repo.findByUsernameAndRole(username, userRole)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return jwt.generateToken(username, user.getRole().name());
    }
}
