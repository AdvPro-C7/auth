package id.ac.ui.cs.advprog.auth.service;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.command.AuthenticateUserCommand;
import id.ac.ui.cs.advprog.auth.service.command.GetUserDetailsCommand;
import id.ac.ui.cs.advprog.auth.service.command.InsertUserCommand;
import id.ac.ui.cs.advprog.auth.service.invoker.AuthenticationInvokerImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InvokerTest {

    @Test
    void authenticateUser_ValidRequest_ReturnsUser() {
        // Arrange
        LoginRequest request = mock(LoginRequest.class);
        User user = mock(User.class);
        AuthenticateUserCommand userAuthenticator = mock(AuthenticateUserCommand.class);
        when(userAuthenticator.execute(any(LoginRequest.class))).thenReturn(user);

        AuthenticationInvokerImpl invoker = new AuthenticationInvokerImpl(
                userAuthenticator,
                mock(GetUserDetailsCommand.class),
                mock(InsertUserCommand.class));

        // Act
        User result = invoker.authenticateUser(request);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void getUserDetails_ValidUid_ReturnsUser() {
        // Arrange
        String uid = "testUid";
        User user = mock(User.class);
        GetUserDetailsCommand userDetailsRetriever = mock(GetUserDetailsCommand.class);
        when(userDetailsRetriever.execute(uid)).thenReturn(user);

        AuthenticationInvokerImpl invoker = new AuthenticationInvokerImpl(
                mock(AuthenticateUserCommand.class),
                userDetailsRetriever,
                mock(InsertUserCommand.class));

        // Act
        User result = invoker.getUserDetails(uid);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void insertUser_ValidRequest_ReturnsTrue() {
        // Arrange
        RegisterRequest request = mock(RegisterRequest.class);
        InsertUserCommand userInserter = mock(InsertUserCommand.class);
        when(userInserter.execute(any(RegisterRequest.class))).thenReturn(true);

        AuthenticationInvokerImpl invoker = new AuthenticationInvokerImpl(
                mock(AuthenticateUserCommand.class),
                mock(GetUserDetailsCommand.class),
                userInserter);

        // Act
        Boolean result = invoker.insertUser(request);

        // Assert
        assertEquals(true, result);
    }
}
