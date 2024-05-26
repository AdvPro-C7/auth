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

        User savedUser = repo.findByEmail(id);
        if (savedUser == null) {
            savedUser = repo.findByNoTelp(id);
        }

        return (savedUser != null && savedUser.getPassword().equals(httpRequest.getPassword())) ? savedUser : null;
    }

    @Override
    public User getUserDetails(String uid) {
        User user = repo.findByEmail(uid);
        if (user != null) {
            return user;
        }

        user = repo.findByNoTelp(uid);
        return user;
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

        User existingUserWithEmail = repo.findByEmail(httpRequest.getEmailAddress());
        if (existingUserWithEmail != null) {
            return false;
        }

        User existingUserWithPhoneNumber = repo.findByNoTelp(httpRequest.getPhoneNumber());
        if (existingUserWithPhoneNumber != null) {
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
