package id.ac.ui.cs.advprog.auth.service.command;

import org.springframework.stereotype.Component;

import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.receiver.AuthenticationReceiverImpl;

@Component
public class InsertUserCommand implements Command<RegisterRequest, Boolean> {
    private final AuthenticationReceiverImpl receiver;

    public InsertUserCommand(AuthenticationReceiverImpl receiver) {
        this.receiver = receiver;
    }

    @Override
    public Boolean execute(RegisterRequest httpRequest) {
        return this.receiver.insertUser(httpRequest);
    }
}
