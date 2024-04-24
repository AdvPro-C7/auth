package id.ac.ui.cs.advprog.auth.service.decorator.decorable;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.decorator.Profile;
import id.ac.ui.cs.advprog.auth.service.decorator.ProfileDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.UserProfile;

public class NameDecorator extends ProfileDecorator {
    private String newName;

    public NameDecorator(UserProfile profile, String newName) {
        super(profile);
        this.newName = newName;
    }

    @Override
    public void updateProfile() {
        User user = ((Profile) decoratedProfile).getUser();
        user.setNama(newName);
        System.out.println("Updating name to: " + newName);
        super.updateProfile();
    }
}