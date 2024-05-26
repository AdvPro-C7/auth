package id.ac.ui.cs.advprog.auth.service.builder;

import java.time.LocalDate;

import id.ac.ui.cs.advprog.auth.model.User;

public class UserProfileBuilder {
    private final User user;

    public UserProfileBuilder(User user) {
        this.user = user;
    }

    public UserProfileBuilder updatePhoto(String newPhoto) {
        user.setFoto(newPhoto);
        return this;
    }

    public UserProfileBuilder updatePassword(String newPassword) {
        user.setPassword(newPassword);
        return this;
    }

    public UserProfileBuilder updateName(String newName) {
        user.setNama(newName);
        return this;
    }

    public UserProfileBuilder updateBirthDate(LocalDate newBirthDate) {
        user.setTanggalLahir(newBirthDate);
        return this;
    }

    public UserProfileBuilder updateBio(String newBio) {
        user.setBio(newBio);
        return this;
    }

    public UserProfileBuilder updateGender(String newGender) {
        user.setJenisKelamin(newGender);
        return this;
    }

    public User build() {
        return user;
    }
}
