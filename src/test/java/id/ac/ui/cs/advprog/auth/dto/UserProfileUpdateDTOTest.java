package id.ac.ui.cs.advprog.auth.dto;

import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserProfileUpdateDTOTest {
    @Test
    public void testUserProfileUpdateDTO() {
        String name = "John Doe";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        String bio = "Software Developer";
        String gender = "Male";
        String password = "password123";
        String photo = "photo.jpg";
        String uid = "123456";

        UserProfileUpdateDTO userProfile = new UserProfileUpdateDTO(name, birthDate, bio, gender, password, photo, uid);

        assertNotNull(userProfile);
        assertEquals(name, userProfile.getName());
        assertEquals(birthDate, userProfile.getBirthDate());
        assertEquals(bio, userProfile.getBio());
        assertEquals(gender, userProfile.getGender());
        assertEquals(password, userProfile.getPassword());
        assertEquals(photo, userProfile.getPhoto());
        assertEquals(uid, userProfile.getUid());
    }

    @Test
    public void testUserProfileUpdateDTO_NullFields() {
        UserProfileUpdateDTO userProfile = new UserProfileUpdateDTO(null, null, null, null, null, null, null);

        assertNotNull(userProfile);
        assertNull(userProfile.getName());
        assertNull(userProfile.getBirthDate());
        assertNull(userProfile.getBio());
        assertNull(userProfile.getGender());
        assertNull(userProfile.getPassword());
        assertNull(userProfile.getPhoto());
        assertNull(userProfile.getUid());
    }

    @Test
    public void testUserProfileUpdateDTO_EmptyStrings() {
        UserProfileUpdateDTO userProfile = new UserProfileUpdateDTO("", LocalDate.of(1990, 1, 1), "", "", "", "", "");

        assertNotNull(userProfile);
        assertEquals("", userProfile.getName());
        assertEquals(LocalDate.of(1990, 1, 1), userProfile.getBirthDate());
        assertEquals("", userProfile.getBio());
        assertEquals("", userProfile.getGender());
        assertEquals("", userProfile.getPassword());
        assertEquals("", userProfile.getPhoto());
        assertEquals("", userProfile.getUid());
    }

    @Test
    public void testUserProfileUpdateDTO_LeapYearDate() {
        LocalDate leapYearDate = LocalDate.of(2000, 2, 29); // Leap year date
        UserProfileUpdateDTO userProfile = new UserProfileUpdateDTO("John Doe", leapYearDate, "Software Developer", "Male", "password123", "photo.jpg", "123456");

        assertNotNull(userProfile);
        assertEquals(leapYearDate, userProfile.getBirthDate());
    }

    @Test
    public void testUserProfileUpdateDTO_InvalidDate() {
        assertThrows(DateTimeException.class, () -> {
            LocalDate invalidDate = LocalDate.of(2024, 2, 30); // Invalid date
            new UserProfileUpdateDTO("John Doe", invalidDate, "Software Developer", "Male", "password123", "photo.jpg", "123456");
        });
    }

    @Test
    public void testUserProfileUpdateDTO_Setters() {
        UserProfileUpdateDTO userProfile = new UserProfileUpdateDTO(null, null, null, null, null, null, null);

        userProfile.setName("Jane Doe");
        userProfile.setBirthDate(LocalDate.of(1995, 5, 15));
        userProfile.setBio("Data Scientist");
        userProfile.setGender("Female");
        userProfile.setPassword("newpassword123");
        userProfile.setPhoto("newphoto.jpg");
        userProfile.setUid("654321");

        assertEquals("Jane Doe", userProfile.getName());
        assertEquals(LocalDate.of(1995, 5, 15), userProfile.getBirthDate());
        assertEquals("Data Scientist", userProfile.getBio());
        assertEquals("Female", userProfile.getGender());
        assertEquals("newpassword123", userProfile.getPassword());
        assertEquals("newphoto.jpg", userProfile.getPhoto());
        assertEquals("654321", userProfile.getUid());
    }
}
