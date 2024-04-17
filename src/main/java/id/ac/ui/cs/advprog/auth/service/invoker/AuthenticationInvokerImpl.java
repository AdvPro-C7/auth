package id.ac.ui.cs.advprog.auth.service.invoker;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.command.AuthenticateUserCommand;
import id.ac.ui.cs.advprog.auth.service.command.CreateTokenCommand;
import id.ac.ui.cs.advprog.auth.service.command.GetUserDetailsCommand;
import id.ac.ui.cs.advprog.auth.service.command.InsertUserCommand;

@Service
public class AuthenticationInvokerImpl implements AuthenticationInvoker {
    private final AuthenticateUserCommand varAuthenticateUserCommand;
    private final CreateTokenCommand varCreateTokenCommand;
    private final GetUserDetailsCommand varGetUserDetailsCommand;
    private final InsertUserCommand varInsertUserCommand;

    public AuthenticationInvokerImpl(
            AuthenticateUserCommand varAuthenticateUserCommand,
            CreateTokenCommand varCreateTokenCommand,
            GetUserDetailsCommand varGetUserDetailsCommand,
            InsertUserCommand varInsertUserCommand) {
        this.varAuthenticateUserCommand = varAuthenticateUserCommand;
        this.varCreateTokenCommand = varCreateTokenCommand;
        this.varGetUserDetailsCommand = varGetUserDetailsCommand;
        this.varInsertUserCommand = varInsertUserCommand;
    }

    @Override
    public Boolean authenticateUser(LoginRequest httpRequest) {
        return this.varAuthenticateUserCommand.execute(httpRequest);
    }

    @Override
    public ResponseCookie createToken(String username) {
        return this.varCreateTokenCommand.execute(username);
    }

    @Override
    public User getUserDetails(HttpServletRequest httpRequest) {
        return this.varGetUserDetailsCommand.execute(httpRequest);
    }

    @Override
    public Boolean insertUser(RegisterRequest httpRequest) {
        return this.varInsertUserCommand.execute(httpRequest);
    }
}
