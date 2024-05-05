package id.ac.ui.cs.advprog.auth.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.builder.UserProfileManager;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

class UserProfileManagerTest {
    private UserProfileManager userProfileManager;
    private User user;

    @SuppressWarnings("unused")
    private byte[] originalPhoto;
    private byte[] newPhoto;

    @BeforeEach
    void setUp() {
        user = new User("John Doe", "john@example.com", "0123456789", "password123");
        userProfileManager = new UserProfileManager();
        originalPhoto = new byte[]{1}; 
    }

    @Test
    void testConstructNameProfile() {
        String expectedNewName = "Jane D";
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
        newPhoto = new byte[]{2}; // New photo data
        User updatedUser = userProfileManager.constructPhotoProfile(user, newPhoto);
        assertArrayEquals(newPhoto, updatedUser.getFoto(), "Photo should be updated to the new byte array.");
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
