package id.ac.ui.cs.advprog.auth.service;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.command.AuthenticateUserCommand;
import id.ac.ui.cs.advprog.auth.service.command.CreateTokenCommand;
import id.ac.ui.cs.advprog.auth.service.command.GetUserDetailsCommand;
import id.ac.ui.cs.advprog.auth.service.command.InsertUserCommand;
import id.ac.ui.cs.advprog.auth.service.receiver.AuthenticationReceiverImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseCookie;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AllCommandsTest {
    @Mock
    private AuthenticationReceiverImpl receiver;

    @Test
    void testAuthenticateUserCommandExecute() {
        // Arrange
        var command = new AuthenticateUserCommand(this.receiver);
        var loginRequest = new LoginRequest();
        when(this.receiver.authenticateUser(loginRequest)).thenReturn(true);

        // Act
        boolean result = command.execute(loginRequest);

        // Assert
        assertTrue(result);
        verify(this.receiver).authenticateUser(loginRequest);
    }

    @Test
    void testCreateTokenCommandExecute() {
        // Arrange
        var command = new CreateTokenCommand(this.receiver);
        var token = ResponseCookie.from("mrt-k-session", "jwt").build();
        when(this.receiver.createToken("bryan")).thenReturn(token);

        // Act
        ResponseCookie result = command.execute("bryan");

        // Assert
        assertNotNull(result);
        assertEquals(token, result);
        verify(this.receiver).createToken("bryan");
    }

    @Test
    void testGetUserDetailsCommandExecute() {
        // Arrange
        var command = new GetUserDetailsCommand(this.receiver);
        var request = mock(HttpServletRequest.class);
        var userDetails = new User("bryan", "bryanilman20@gmail.com", "00008888", "helloworld");
        when(this.receiver.getUserDetails(request)).thenReturn(userDetails);

        // Act
        User result = command.execute(request);

        // Assert
        assertEquals(userDetails, result);
    }

    @Test
    void testInsertUserCommandExecute() {
        // Arrange
        var command = new InsertUserCommand(this.receiver);
        var registerRequest = new RegisterRequest();
        when(this.receiver.insertUser(registerRequest)).thenReturn(true);

        // Act
        boolean result = command.execute(registerRequest);

        // Assert
        assertEquals(true, result);
    }
}
