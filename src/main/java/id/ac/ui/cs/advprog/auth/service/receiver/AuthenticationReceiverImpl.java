package id.ac.ui.cs.advprog.auth.service.receiver;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationReceiverImpl implements AuthenticationReceiver {
    private final UserRepository repo;

    public AuthenticationReceiverImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User authenticateUser(LoginRequest httpRequest) {
        if (httpRequest == null || httpRequest.getId() == null || httpRequest.getPassword() == null) {
            return null;
        }

        String id = httpRequest.getId();
        User savedUser = null;

        if (repo.existsByEmail(id)) {
            savedUser = repo.findByEmail(id);
        } else if (repo.existsByNoTelp(id)) {
            savedUser = repo.findByNoTelp(id);
        }

        return (savedUser != null && savedUser.getPassword().equals(httpRequest.getPassword())) ? savedUser : null;
    }

    @Override
    public User getUserDetails(String uid) {
        if (repo.existsByEmail(uid)) {
            return repo.findByEmail(uid);
        } else if (repo.existsByNoTelp(uid)) {
            return repo.findByNoTelp(uid);
        }

        return null;
    }

    @Override
    public Boolean insertUser(RegisterRequest httpRequest) {
        if (httpRequest == null ||
                httpRequest.getName() == null ||
                httpRequest.getEmailAddress() == null ||
                httpRequest.getPhoneNumber() == null ||
                httpRequest.getPassword() == null ||
                httpRequest.getName().contains("#")) {
            return false;
        }

        if (repo.existsByEmail(httpRequest.getEmailAddress()) ||
                repo.existsByNoTelp(httpRequest.getPhoneNumber())) {
            return false;
        }

        User newUser = new User(
                httpRequest.getName(),
                httpRequest.getEmailAddress(),
                httpRequest.getPhoneNumber(),
                httpRequest.getPassword());

        repo.save(newUser);

        return true;
    }
}
