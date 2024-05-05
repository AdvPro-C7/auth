package id.ac.ui.cs.advprog.auth.controller;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.builder.UserProfileManager;
import id.ac.ui.cs.advprog.auth.service.builder.UserProfileUpdateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @Mock
    private UserProfileManager userProfileManager;

    @InjectMocks
    private ProfileController profileController;

    @Test
    public void testUpdateProfile() throws Exception {
        UserProfileUpdateDTO updates = new UserProfileUpdateDTO();
        updates.setName("John Doe");
        updates.setBirthDate(LocalDate.of(1990, 5, 15));
        updates.setBio("Software Engineer");
        updates.setGender("Male");
        updates.setPassword("newpassword");

        User user = new User("Alice", "alice@example.com", "123456789", "password");

        when(userProfileManager.constructNameProfile(any(User.class), any(String.class))).thenReturn(user);
        when(userProfileManager.constructBirthDateProfile(any(User.class), any(LocalDate.class))).thenReturn(user);
        when(userProfileManager.constructBioProfile(any(User.class), any(String.class))).thenReturn(user);
        when(userProfileManager.constructGenderProfile(any(User.class), any(String.class))).thenReturn(user);
        when(userProfileManager.constructPasswordProfile(any(User.class), any(String.class))).thenReturn(user);

        ResponseEntity<User> responseEntity = profileController.updateProfile(updates, user);

        assertEquals(user, responseEntity.getBody());
    }
}
