package id.ac.ui.cs.advprog.auth.service.command;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.receiver.AuthenticationReceiverImpl;

@Component
public class GetUserDetailsCommand implements Command<HttpServletRequest, User> {
    private final AuthenticationReceiverImpl receiver;

    public GetUserDetailsCommand(AuthenticationReceiverImpl receiver) {
        this.receiver = receiver;
    }

    @Override
    public User execute(HttpServletRequest httpRequest) {
        return this.receiver.getUserDetails(httpRequest);
    }
}
