package id.ac.ui.cs.advprog.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.auth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByNoTelp(String noTelp);

    Boolean existsByEmail(String email);

    Boolean existsByNoTelp(String noTelp);
}
