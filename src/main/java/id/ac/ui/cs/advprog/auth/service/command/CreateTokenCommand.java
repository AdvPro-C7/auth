package id.ac.ui.cs.advprog.auth.service.command;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import id.ac.ui.cs.advprog.auth.service.receiver.AuthenticationReceiverImpl;

@Component
public class CreateTokenCommand implements Command<String, ResponseCookie> {
    private final AuthenticationReceiverImpl receiver;

    public CreateTokenCommand(AuthenticationReceiverImpl receiver) {
        this.receiver = receiver;
    }

    @Override
    public ResponseCookie execute(String username) {
        return this.receiver.createToken(username);
    }
}
