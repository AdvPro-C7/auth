package id.ac.ui.cs.advprog.auth.controller;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.invoker.AuthenticationInvokerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationControllerTest {

        @Test
        void registerUser_SuccessfulRegistration_ReturnsOk() {
                // Arrange
                RegisterRequest request = mock(RegisterRequest.class);
                AuthenticationInvokerImpl service = mock(AuthenticationInvokerImpl.class);
                when(service.insertUser(any(RegisterRequest.class))).thenReturn(true);

                AuthenticationController controller = new AuthenticationController(service);

                // Act
                ResponseEntity<Map<String, String>> response = controller.registerUser(request);

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(Collections.singletonMap("message", "registration successful"), response.getBody());
        }

        @Test
        void registerUser_FailedRegistration_ReturnsBadRequest() {
                // Arrange
                RegisterRequest request = mock(RegisterRequest.class);
                AuthenticationInvokerImpl service = mock(AuthenticationInvokerImpl.class);
                when(service.insertUser(any(RegisterRequest.class))).thenReturn(false);

                AuthenticationController controller = new AuthenticationController(service);

                // Act
                ResponseEntity<Map<String, String>> response = controller.registerUser(request);

                // Assert
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
                assertEquals(Collections.singletonMap("message", "registration failed"), response.getBody());
        }

        @Test
        void loginUser_ValidCredentials_ReturnsOk() {
                // Arrange
                LoginRequest request = mock(LoginRequest.class);
                User user = mock(User.class);
                AuthenticationInvokerImpl service = mock(AuthenticationInvokerImpl.class);
                when(service.authenticateUser(any(LoginRequest.class))).thenReturn(user);

                AuthenticationController controller = new AuthenticationController(service);

                // Act
                ResponseEntity<Map<String, Object>> response = controller.loginUser(request);

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                Map<String, Object> expectedBody = new HashMap<>();
                expectedBody.put("message", "login successful");
                expectedBody.put("user", user);
                assertEquals(expectedBody, response.getBody());
        }

        @Test
        void loginUser_InvalidCredentials_ReturnsBadRequest() {
                // Arrange
                LoginRequest request = mock(LoginRequest.class);
                AuthenticationInvokerImpl service = mock(AuthenticationInvokerImpl.class);
                when(service.authenticateUser(any(LoginRequest.class))).thenReturn(null);

                AuthenticationController controller = new AuthenticationController(service);

                // Act
                ResponseEntity<Map<String, Object>> response = controller.loginUser(request);

                // Assert
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
                assertEquals(Collections.singletonMap("message", "invalid credentials"), response.getBody());
        }

        @Test
        void getUserDetails_ValidUID_ReturnsOkWithUserDetails() {
                // Arrange
                String uid = "testUID";
                User user = mock(User.class);
                AuthenticationInvokerImpl service = mock(AuthenticationInvokerImpl.class);
                when(service.getUserDetails(anyString())).thenReturn(user);

                AuthenticationController controller = new AuthenticationController(service);

                // Act
                ResponseEntity<User> response = controller.getUserDetails(uid);

                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(user, response.getBody());
        }

        @Test
        void getUserDetails_InvalidUID_ReturnsBadRequest() {
                // Arrange
                String uid = "invalidUID";
                AuthenticationInvokerImpl service = mock(AuthenticationInvokerImpl.class);
                when(service.getUserDetails(anyString())).thenReturn(null);

                AuthenticationController controller = new AuthenticationController(service);

                // Act
                ResponseEntity<User> response = controller.getUserDetails(uid);

                // Assert
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
                assertEquals(null, response.getBody());
        }
}
