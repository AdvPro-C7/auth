package id.ac.ui.cs.advprog.auth.service;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.repository.UserRepository;
import id.ac.ui.cs.advprog.auth.service.builder.UserProfileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserProfileManagerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserProfileManager userProfileManager;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("John Doe", "john@example.com", "0123456789", "password123");
        // Ensure userRepository.save() returns the updated user object
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testConstructNameProfile() {
        String expectedNewName = "Jane Doe";
        User updatedUser = userProfileManager.constructNameProfile(user, expectedNewName);
        assertEquals(expectedNewName, updatedUser.getNama(), "Name should be updated to Jane Doe.");
    }

    @Test
    void testConstructPasswordProfile() {
        String expectedNewPassword = "newSecurePassword123";
        User updatedUser = userProfileManager.constructPasswordProfile(user, expectedNewPassword);
        assertEquals(expectedNewPassword, updatedUser.getPassword(), "Password should be updated.");
    }

    @Test
    void testConstructPhotoProfile() {
        String newPhoto = "newPhotoUrl";
        User updatedUser = userProfileManager.constructPhotoProfile(user, newPhoto);
        assertEquals(newPhoto, updatedUser.getFoto(), "Photo should be updated to the new string.");
    }

    @Test
    void testConstructBioProfile() {
        String expectedNewBio = "Updated bio";
        User updatedUser = userProfileManager.constructBioProfile(user, expectedNewBio);
        assertEquals(expectedNewBio, updatedUser.getBio(), "Bio should be updated.");
    }

    @Test
    void testConstructBirthDateProfile() {
        LocalDate expectedNewBirthDate = LocalDate.of(1995, 5, 23);
        User updatedUser = userProfileManager.constructBirthDateProfile(user, expectedNewBirthDate);
        assertEquals(expectedNewBirthDate, updatedUser.getTanggalLahir(), "Birth date should be updated.");
    }

    @Test
    void testConstructGenderProfile() {
        String newGender = "Female";
        User updatedUser = userProfileManager.constructGenderProfile(user, newGender);
        assertEquals(newGender, updatedUser.getJenisKelamin(), "Gender should be updated to Female.");
    }
}
