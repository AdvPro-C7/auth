package id.ac.ui.cs.advprog.auth.service.decorator;

import id.ac.ui.cs.advprog.auth.model.User;

public class Profile implements UserProfile {
    private User user;

    public Profile(User user) {
        this.user = user;
    }

    @Override
    public String getDetails() {
        return "Nama: " + user.getNama() + ", Email: " + user.getEmail();
    }

    @Override
    public void updateProfile() {
        System.out.println("Profile updated with basic information.");
    }

    public User getUser() {
        return user;
    }
}
