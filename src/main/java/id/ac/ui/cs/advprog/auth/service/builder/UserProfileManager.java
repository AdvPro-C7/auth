package id.ac.ui.cs.advprog.auth.service.builder;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.repository.UserRepository;

@Service
public class UserProfileManager {

    private final UserRepository userRepository;

    public UserProfileManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User constructPhotoProfile(User user, String newPhoto) {
        User updatedUser = new UserProfileBuilder(user)
                .updatePhoto(newPhoto)
                .build();
        return userRepository.save(updatedUser);
    }

    public User constructPasswordProfile(User user, String newPassword) {
        User updatedUser = new UserProfileBuilder(user)
                .updatePassword(newPassword)
                .build();
        return userRepository.save(updatedUser);
    }

    public User constructNameProfile(User user, String newName) {
        User updatedUser = new UserProfileBuilder(user)
                .updateName(newName)
                .build();
        return userRepository.save(updatedUser);
    }

    public User constructBirthDateProfile(User user, LocalDate newBirthDate) {
        User updatedUser = new UserProfileBuilder(user)
                .updateBirthDate(newBirthDate)
                .build();
        return userRepository.save(updatedUser);
    }

    public User constructBioProfile(User user, String newBio) {
        User updatedUser = new UserProfileBuilder(user)
                .updateBio(newBio)
                .build();
        return userRepository.save(updatedUser);
    }

    public User constructGenderProfile(User user, String newGender) {
        User updatedUser = new UserProfileBuilder(user)
                .updateGender(newGender)
                .build();
        return userRepository.save(updatedUser);
    }
}
