package id.ac.ui.cs.advprog.auth.service;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.command.AuthenticateUserCommand;
import id.ac.ui.cs.advprog.auth.service.command.CreateTokenCommand;
import id.ac.ui.cs.advprog.auth.service.command.GetUserDetailsCommand;
import id.ac.ui.cs.advprog.auth.service.command.InsertUserCommand;
import id.ac.ui.cs.advprog.auth.service.invoker.AuthenticationInvokerImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseCookie;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class InvokerTest {

    @Autowired
    private AuthenticationInvokerImpl service;

    @Test
    void testAuthenticateUser() {
        var authenticateUserCommand = mock(AuthenticateUserCommand.class);
        ReflectionTestUtils.setField(this.service, "varAuthenticateUserCommand", authenticateUserCommand);

        var loginRequest = new LoginRequest();
        when(authenticateUserCommand.execute(loginRequest)).thenReturn(true);

        assertTrue(this.service.authenticateUser(loginRequest));
        verify(authenticateUserCommand).execute(loginRequest);
    }

    @Test
    void testCreateToken() {
        var createTokenCommand = mock(CreateTokenCommand.class);
        ReflectionTestUtils.setField(this.service, "varCreateTokenCommand", createTokenCommand);

        var responseCookie = ResponseCookie.from("token", "value").build();
        when(createTokenCommand.execute("bryan")).thenReturn(responseCookie);

        assertEquals(responseCookie, this.service.createToken("bryan"));
        verify(createTokenCommand).execute("bryan");
    }

    @Test
    void testGetUserDetails() {
        var getUserDetailsCommand = mock(GetUserDetailsCommand.class);
        ReflectionTestUtils.setField(this.service, "varGetUserDetailsCommand", getUserDetailsCommand);

        var request = mock(HttpServletRequest.class);
        var userDetails = new User("bryan", "bryanilman20@gmail.com", "00008888", "helloworld");
        when(getUserDetailsCommand.execute(request)).thenReturn(userDetails);

        assertEquals(userDetails, this.service.getUserDetails(request));
        verify(getUserDetailsCommand).execute(request);
    }

    @Test
    void testInsertUser() {
        var insertUserCommand = mock(InsertUserCommand.class);
        ReflectionTestUtils.setField(this.service, "varInsertUserCommand", insertUserCommand);

        var registerRequest = new RegisterRequest("bryan", "bryanilman20@gmail.com", "00008888", "helloworld");
        when(insertUserCommand.execute(registerRequest)).thenReturn(true);

        assertEquals(true, this.service.insertUser(registerRequest));
        verify(insertUserCommand).execute(registerRequest);
    }
}
