package id.ac.ui.cs.advprog.auth.service;

import id.ac.ui.cs.advprog.auth.AppProperties;
import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.repository.UserRepository;
import id.ac.ui.cs.advprog.auth.service.receiver.AuthenticationReceiverImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseCookie;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReceiverTest {
    @Mock
    private UserRepository repo;

    @Mock
    private AppProperties props;

    @InjectMocks
    private AuthenticationReceiverImpl authenticationReceiver;

    @Test
    void testAuthenticateUser() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("whoami@hotmail.com", "helloworld");
        when(repo.existsByEmail(anyString())).thenReturn(true);
        when(repo.findByEmail(anyString()))
                .thenReturn(new User("bryan", "whoami@hotmail.com", "00008888", "helloworld"));
        ReflectionTestUtils.setField(this.authenticationReceiver, "repo", repo);

        // Act
        Boolean result = authenticationReceiver.authenticateUser(loginRequest);

        // Assert
        assertTrue(result);
    }

    @Test
    void testCreateToken() {
        // Arrange
        when(props.getKey()).thenReturn("key");
        when(props.getSecret()).thenReturn("secret");
        ReflectionTestUtils.setField(this.authenticationReceiver, "props", props);

        // Act
        ResponseCookie result = authenticationReceiver.createToken("bryan");

        // Assert
        assertNotNull(result);
    }

    @Test
    void testGetUserDetails() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        when(props.getKey()).thenReturn("key");
        when(props.getSecret()).thenReturn("secret");
        when(repo.existsByEmail(anyString())).thenReturn(true);
        when(repo.findByEmail(anyString()))
                .thenReturn(new User("bryan", "whoami@hotmail.com", "00008888", "helloworld"));
        ReflectionTestUtils.setField(this.authenticationReceiver, "props", props);
        ReflectionTestUtils.setField(this.authenticationReceiver, "repo", repo);

        // Act
        User result = authenticationReceiver.getUserDetails(request);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testInsertUser() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest("bryan", "whoami@hotmail.com", "00008888",
                "helloworld");
        when(repo.existsByEmail(anyString())).thenReturn(false);
        when(repo.existsByNoTelp(anyString())).thenReturn(false);
        ReflectionTestUtils.setField(this.authenticationReceiver, "repo", repo);

        // Act
        Boolean result = authenticationReceiver.insertUser(registerRequest);

        // Assert
        assertTrue(result);
    }
}