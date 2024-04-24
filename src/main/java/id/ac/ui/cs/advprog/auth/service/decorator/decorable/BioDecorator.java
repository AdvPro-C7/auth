package id.ac.ui.cs.advprog.auth.service.decorator.decorable;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.decorator.Profile;
import id.ac.ui.cs.advprog.auth.service.decorator.ProfileDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.UserProfile;

public class BioDecorator extends ProfileDecorator {
    private String newBio;

    public BioDecorator(UserProfile profile, String newBio) {
        super(profile);
        this.newBio = newBio;
    }

    @Override
    public void updateProfile() {
        User user = ((Profile) decoratedProfile).getUser();
        user.setBio(newBio);
        System.out.println("Updating bio to: " + newBio);
        super.updateProfile();
    }
}