package id.ac.ui.cs.advprog.auth.service.decorator.decorable;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.decorator.Profile;
import id.ac.ui.cs.advprog.auth.service.decorator.ProfileDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.UserProfile;

public class PasswordDecorator extends ProfileDecorator {
    private String newPassword;

    public PasswordDecorator(UserProfile profile, String newPassword) {
        super(profile);
        this.newPassword = newPassword;
    }

    @Override
    public void updateProfile() {
        User user = ((Profile) decoratedProfile).getUser();
        user.setPassword(newPassword);
        System.out.println("Updating password to: " + newPassword);
        super.updateProfile();
    }
}
