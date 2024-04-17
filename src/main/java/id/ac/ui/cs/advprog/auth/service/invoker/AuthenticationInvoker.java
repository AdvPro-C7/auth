package id.ac.ui.cs.advprog.auth.service.invoker;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;

public interface AuthenticationInvoker {
    Boolean authenticateUser(LoginRequest httpRequest);

    ResponseCookie createToken(String username);

    User getUserDetails(HttpServletRequest httpRequest);

    Boolean insertUser(RegisterRequest httpRequest);
}
