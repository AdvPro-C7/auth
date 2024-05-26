package id.ac.ui.cs.advprog.auth.dto;

import id.ac.ui.cs.advprog.auth.model.dto.UserProfileUpdateDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
