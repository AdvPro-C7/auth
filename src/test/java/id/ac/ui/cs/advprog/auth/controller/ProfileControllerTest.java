package id.ac.ui.cs.advprog.auth.controller;

import id.ac.ui.cs.advprog.auth.dto.UserProfileUpdateDTO;
import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.builder.UserProfileManager;
import id.ac.ui.cs.advprog.auth.service.invoker.AuthenticationInvokerImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {
    @Mock
    private UserProfileManager userProfileManager;

    @InjectMocks
    private ProfileController profileController;

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
}
