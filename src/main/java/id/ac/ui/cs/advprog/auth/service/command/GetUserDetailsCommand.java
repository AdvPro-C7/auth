package id.ac.ui.cs.advprog.auth.service.command;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.receiver.AuthenticationReceiverImpl;

import org.springframework.stereotype.Component;

@Component
public class GetUserDetailsCommand implements Command<String, User> {
    private final AuthenticationReceiverImpl receiver;

    public GetUserDetailsCommand(AuthenticationReceiverImpl receiver) {
        this.receiver = receiver;
    }

    @Override
    public User execute(String uid) {
        return this.receiver.getUserDetails(uid);
    }
}
