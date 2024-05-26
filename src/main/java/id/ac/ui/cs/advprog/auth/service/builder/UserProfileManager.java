package id.ac.ui.cs.advprog.auth.service.builder;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.auth.model.User;

@Service
public class UserProfileManager {

    public User constructProfilInfo(User user) {
        return new UserProfileBuilder(user)
                .updateInfo()
                .build();
    }

    public User constructPhotoProfile(User user, String newPhoto) {
        return new UserProfileBuilder(user)
                .updatePhoto(newPhoto)
                .build();
    }

    public User constructPasswordProfile(User user, String newPassword) {
        return new UserProfileBuilder(user)
                .updatePassword(newPassword)
                .build();
    }

    public User constructNameProfile(User user, String newName) {
        return new UserProfileBuilder(user)
                .updateName(newName)
                .build();
    }

    public User constructBirthDateProfile(User user, LocalDate newBirthDate) {
        return new UserProfileBuilder(user)
                .updateBirthDate(newBirthDate)
                .build();
    }

    public User constructBioProfile(User user, String newBio) {
        return new UserProfileBuilder(user)
                .updateBio(newBio)
                .build();
    }

    public User constructGenderProfile(User user, String newGender) {
        return new UserProfileBuilder(user)
                .updateGender(newGender)
                .build();
    }
}
