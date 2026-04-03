package assessment.Zorvyn_Project.repository;


import assessment.Zorvyn_Project.entity.Role;
import assessment.Zorvyn_Project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndRole(String username, Role role);
    boolean existsByUsernameAndRole(String username, Role role);
    Optional<User> findByUsername(String username);


}
