package id.ac.ui.cs.advprog.auth.service.receiver;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;

public interface AuthenticationReceiver {
    User authenticateUser(LoginRequest httpRequest);

    User getUserDetails(String uid);

    Boolean insertUser(RegisterRequest httpRequest);
}
