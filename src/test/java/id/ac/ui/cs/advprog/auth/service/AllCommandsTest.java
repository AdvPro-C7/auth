package id.ac.ui.cs.advprog.auth.service;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.command.AuthenticateUserCommand;
import id.ac.ui.cs.advprog.auth.service.command.GetUserDetailsCommand;
import id.ac.ui.cs.advprog.auth.service.command.InsertUserCommand;
import id.ac.ui.cs.advprog.auth.service.receiver.AuthenticationReceiverImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AllCommandsTest {
    @Test
    void authenticateUserCommand_Execute_ReturnsUser() {
        // Arrange
        LoginRequest request = mock(LoginRequest.class);
        User user = mock(User.class);
        AuthenticationReceiverImpl receiver = mock(AuthenticationReceiverImpl.class);
        when(receiver.authenticateUser(any(LoginRequest.class))).thenReturn(user);

        AuthenticateUserCommand command = new AuthenticateUserCommand(receiver);

        // Act
        User result = command.execute(request);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void getUserDetailsCommand_Execute_ReturnsUser() {
        // Arrange
        String uid = "testUID";
        User user = mock(User.class);
        AuthenticationReceiverImpl receiver = mock(AuthenticationReceiverImpl.class);
        when(receiver.getUserDetails(uid)).thenReturn(user);

        GetUserDetailsCommand command = new GetUserDetailsCommand(receiver);

        // Act
        User result = command.execute(uid);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void insertUserCommand_Execute_ReturnsBoolean() {
        // Arrange
        RegisterRequest request = mock(RegisterRequest.class);
        AuthenticationReceiverImpl receiver = mock(AuthenticationReceiverImpl.class);
        when(receiver.insertUser(any(RegisterRequest.class))).thenReturn(true);

        InsertUserCommand command = new InsertUserCommand(receiver);

        // Act
        Boolean result = command.execute(request);

        // Assert
        assertEquals(true, result);
    }
}
