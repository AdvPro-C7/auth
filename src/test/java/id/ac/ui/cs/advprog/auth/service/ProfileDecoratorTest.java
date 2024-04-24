package id.ac.ui.cs.advprog.auth.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.decorator.Profile;
import id.ac.ui.cs.advprog.auth.service.decorator.UserProfile;
import id.ac.ui.cs.advprog.auth.service.decorator.decorable.BioDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.decorable.BirthDateDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.decorable.GenderDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.decorable.NameDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.decorable.PasswordDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.decorable.PhotoDecorator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

class ProfileDecoratorTest {
    private User user;
    private UserProfile profile;
    private byte[] originalPhoto;
    private byte[] newPhoto;

    @BeforeEach
    void setUp() {
        user = new User("John Doe", "john@example.com", "0123456789", "password123");
        profile = new Profile(user);
        originalPhoto = new byte[]{1}; 
    }

    @Test
    void testUpdateName() {
        String expectedNewName = "Jane Doe";
        profile = new NameDecorator(profile, expectedNewName);
        profile.updateProfile();
        assertEquals(expectedNewName, user.getNama(), "Name should be updated to Jane Doe.");
    }

    @Test
    void testUpdatePassword() {
        String expectedNewPassword = "newSecurePassword123";
        profile = new PasswordDecorator(profile, expectedNewPassword);
        profile.updateProfile();
        assertEquals(expectedNewPassword, user.getPassword(), "Password should be updated.");
    }

    @Test
    void testUpdatePhoto() {
        newPhoto = new byte[]{2}; // New photo data
        profile = new PhotoDecorator(profile, newPhoto);
        profile.updateProfile();
        
        assertArrayEquals(newPhoto, user.getFoto(), "Photo should be updated to the new byte array.");
    }

    @Test
    void testUpdateBio() {
        String expectedNewBio = "Updated bio";
        profile = new BioDecorator(profile, expectedNewBio);
        profile.updateProfile();
        assertEquals(expectedNewBio, user.getBio(), "Bio should be updated.");
    }

    @Test
    void testUpdateGender() {
        String expectedNewGender = "Female";
        profile = new GenderDecorator(profile, expectedNewGender);
        profile.updateProfile();
        assertEquals(expectedNewGender, user.getJenisKelamin(), "Gender should be updated.");
    }

    @Test
    void testUpdateBirthDate() {
        LocalDate expectedNewBirthDate = LocalDate.of(1995, 5, 23);
        profile = new BirthDateDecorator(profile, expectedNewBirthDate);
        profile.updateProfile();
        assertEquals(expectedNewBirthDate, user.getTanggalLahir(), "Birth date should be updated.");
    }
}
