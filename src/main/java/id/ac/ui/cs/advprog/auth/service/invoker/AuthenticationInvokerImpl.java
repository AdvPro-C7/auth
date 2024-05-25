package id.ac.ui.cs.advprog.auth.service.invoker;

import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.command.AuthenticateUserCommand;
import id.ac.ui.cs.advprog.auth.service.command.GetUserDetailsCommand;
import id.ac.ui.cs.advprog.auth.service.command.InsertUserCommand;

@Service
public class AuthenticationInvokerImpl implements AuthenticationInvoker {
    private final AuthenticateUserCommand userAuthenticator;
    private final GetUserDetailsCommand userDetailsRetriever;
    private final InsertUserCommand userInserter;

    public AuthenticationInvokerImpl(
            AuthenticateUserCommand userAuthenticator,
            GetUserDetailsCommand userDetailsRetriever,
            InsertUserCommand userInserter) {
        this.userAuthenticator = userAuthenticator;
        this.userDetailsRetriever = userDetailsRetriever;
        this.userInserter = userInserter;
    }

    @Override
    public User authenticateUser(LoginRequest httpRequest) {
        return this.userAuthenticator.execute(httpRequest);
    }

    @Override
    public User getUserDetails(String uid) {
        return this.userDetailsRetriever.execute(uid);
    }

    @Override
    public Boolean insertUser(RegisterRequest httpRequest) {
        return this.userInserter.execute(httpRequest);
    }
}
