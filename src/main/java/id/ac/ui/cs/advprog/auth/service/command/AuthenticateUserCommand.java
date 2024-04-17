package id.ac.ui.cs.advprog.auth.service.command;

import org.springframework.stereotype.Component;

import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.service.receiver.AuthenticationReceiverImpl;

@Component
public class AuthenticateUserCommand implements Command<LoginRequest, Boolean> {
    private final AuthenticationReceiverImpl receiver;

    public AuthenticateUserCommand(AuthenticationReceiverImpl receiver) {
        this.receiver = receiver;
    }

    @Override
    public Boolean execute(LoginRequest httpRequest) {
        return this.receiver.authenticateUser(httpRequest);
    }
}
