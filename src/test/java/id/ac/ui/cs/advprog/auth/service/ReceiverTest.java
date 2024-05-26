package id.ac.ui.cs.advprog.auth.service;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.repository.UserRepository;
import id.ac.ui.cs.advprog.auth.service.receiver.AuthenticationReceiverImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReceiverTest {

    @Test
    void authenticateUser_ValidRequest_ReturnsUser() {
        // Arrange
        LoginRequest request = new LoginRequest("testId", "testPassword");
        User user = new User("testName", "testEmail", "testPhone", "testPassword");
        UserRepository repo = mock(UserRepository.class);
        when(repo.findByEmail(request.getId())).thenReturn(user);

        AuthenticationReceiverImpl receiver = new AuthenticationReceiverImpl(repo);

        // Act
        User result = receiver.authenticateUser(request);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void authenticateUser_InvalidRequest_ReturnsNull() {
        // Arrange
        LoginRequest request = new LoginRequest(null, null);
        UserRepository repo = mock(UserRepository.class);

        AuthenticationReceiverImpl receiver = new AuthenticationReceiverImpl(repo);

        // Act
        User result = receiver.authenticateUser(request);

        // Assert
        assertNull(result);
    }

    @Test
    void getUserDetails_ValidUID_ReturnsUser() {
        // Arrange
        String uid = "testUID";
        User user = new User("testName", "testEmail", "testPhone", "testPassword");
        UserRepository repo = mock(UserRepository.class);
        when(repo.findByEmail(uid)).thenReturn(user);

        AuthenticationReceiverImpl receiver = new AuthenticationReceiverImpl(repo);

        // Act
        User result = receiver.getUserDetails(uid);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void getUserDetails_InvalidUID_ReturnsNull() {
        // Arrange
        String uid = "invalidUID";
        UserRepository repo = mock(UserRepository.class);

        AuthenticationReceiverImpl receiver = new AuthenticationReceiverImpl(repo);

        // Act
        User result = receiver.getUserDetails(uid);

        // Assert
        assertNull(result);
    }

    @Test
    void insertUser_ValidRequest_ReturnsTrue() {
        // Arrange
        RegisterRequest request = new RegisterRequest("testName", "testEmail", "testPhone", "testPassword");
        UserRepository repo = mock(UserRepository.class);
        when(repo.findByEmail(request.getEmailAddress())).thenReturn(null);
        when(repo.findByNoTelp(request.getPhoneNumber())).thenReturn(null);

        AuthenticationReceiverImpl receiver = new AuthenticationReceiverImpl(repo);

        // Act
        Boolean result = receiver.insertUser(request);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void insertUser_InvalidRequest_ReturnsFalse() {
        // Arrange
        RegisterRequest request = new RegisterRequest(null, null, null, null);
        UserRepository repo = mock(UserRepository.class);

        AuthenticationReceiverImpl receiver = new AuthenticationReceiverImpl(repo);

        // Act
        Boolean result = receiver.insertUser(request);

        // Assert
        assertEquals(false, result);
    }
}
