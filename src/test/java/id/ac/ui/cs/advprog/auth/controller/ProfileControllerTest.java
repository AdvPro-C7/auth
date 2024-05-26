package id.ac.ui.cs.advprog.auth.controller;

import id.ac.ui.cs.advprog.auth.model.dto.UserProfileUpdateDTO;
import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.builder.UserProfileManager;
import id.ac.ui.cs.advprog.auth.service.invoker.AuthenticationInvokerImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @Test
    void updateProfile_ValidUpdates_ReturnsOkWithUpdatedUser() {
        // Arrange
        String uid = "testUID";
        User user = mock(User.class);
        AuthenticationInvokerImpl service = mock(AuthenticationInvokerImpl.class);
        UserProfileManager userProfileManager = mock(UserProfileManager.class);

        when(service.getUserDetails(anyString())).thenReturn(user);
        when(userProfileManager.constructNameProfile(user, "John Doe")).thenReturn(user);
        when(userProfileManager.constructBirthDateProfile(user, LocalDate.of(1990, 1, 1))).thenReturn(user);
        when(userProfileManager.constructBioProfile(user, "Software Developer")).thenReturn(user);
        when(userProfileManager.constructGenderProfile(user, "Male")).thenReturn(user);
        when(userProfileManager.constructPasswordProfile(user, "password123")).thenReturn(user);
        when(userProfileManager.constructPhotoProfile(user, "photo.jpg")).thenReturn(user);

        ProfileController controller = new ProfileController(service, userProfileManager);

        UserProfileUpdateDTO updates = new UserProfileUpdateDTO(
                "John Doe", LocalDate.of(1990, 1, 1), "Software Developer", "Male", "password123", "photo.jpg", uid);

        // Act
        ResponseEntity<User> response = controller.updateProfile(updates);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void updateProfile_InvalidUID_ReturnsBadRequest() {
        // Arrange
        String uid = "invalidUID";
        AuthenticationInvokerImpl service = mock(AuthenticationInvokerImpl.class);
        UserProfileManager userProfileManager = mock(UserProfileManager.class);

        when(service.getUserDetails(anyString())).thenReturn(null);

        ProfileController controller = new ProfileController(service, userProfileManager);

        UserProfileUpdateDTO updates = new UserProfileUpdateDTO(
                "John Doe", LocalDate.of(1990, 1, 1), "Software Developer", "Male", "password123", "photo.jpg", uid);

        // Act
        ResponseEntity<User> response = controller.updateProfile(updates);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
}
