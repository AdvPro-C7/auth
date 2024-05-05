package id.ac.ui.cs.advprog.auth.service.builder;

import java.time.LocalDate;

import id.ac.ui.cs.advprog.auth.model.User;

public class UserProfileBuilder {
    private User user;

    public UserProfileBuilder(User user) {
        this.user = user;
    }

    public UserProfileBuilder updateInfo() {
        System.out.println("Updating profile information.");
        return this;
    }

    public UserProfileBuilder updatePhoto(byte[] newPhoto) {
        user.setFoto(newPhoto);
        System.out.println("Updating profile photo.");
        return this;
    }

    public UserProfileBuilder updatePassword(String newPassword) {
        user.setPassword(newPassword);
        System.out.println("Updating password to: " + newPassword);
        return this;
    }

    public UserProfileBuilder updateName(String newName) {
        user.setNama(newName);
        System.out.println("Updating name to: " + newName);
        return this;
    }

    public UserProfileBuilder updateBirthDate(LocalDate newBirthDate) {
        user.setTanggalLahir(newBirthDate);
        System.out.println("Updating birth date to: " + newBirthDate);
        return this;
    }

    public UserProfileBuilder updateBio(String newBio) {
        user.setBio(newBio);
        System.out.println("Updating bio to: " + newBio);
        return this;
    }

    public UserProfileBuilder updateGender(String newGender) {
        user.setJenisKelamin(newGender);
        System.out.println("Updating gender to " + newGender);
        return this;
    }

    public User build() {
        return user;
    }
}
