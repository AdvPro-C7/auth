package id.ac.ui.cs.advprog.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseCookie;
import org.springframework.test.util.ReflectionTestUtils;

import id.ac.ui.cs.advprog.auth.AppProperties;
import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.repository.UserRepository;
import id.ac.ui.cs.advprog.auth.service.receiver.AuthenticationReceiverImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

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
        String jwt = Jwts.builder()
                .setSubject("abcd@gmail.com")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(86400)))
                .signWith(SignatureAlgorithm.HS512, "secret").compact();

        var token = new Cookie("key", jwt);

        var request = mock(HttpServletRequest.class);
        when(request.getCookies()).thenReturn(new Cookie[] { token });

        var user = new User("username", "abcd@gmail.com", "44448888", "password");

        when(this.repo.existsByEmail("abcd@gmail.com")).thenReturn(true);
        when(this.repo.existsByNoTelp("abcd@gmail.com")).thenReturn(false);

        when(this.repo.findByEmail("abcd@gmail.com")).thenReturn(user);

        when(this.props.getKey()).thenReturn("key");
        when(this.props.getSecret()).thenReturn("secret");

        ReflectionTestUtils.setField(this.authenticationReceiver, "props", this.props);
        ReflectionTestUtils.setField(this.authenticationReceiver, "repo", this.repo);

        // Act
        var result = this.authenticationReceiver.getUserDetails(request);

        // Assert
        assertEquals(user.getId(), result.getId());
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