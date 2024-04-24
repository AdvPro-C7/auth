package id.ac.ui.cs.advprog.auth.service.decorator.decorable;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.decorator.Profile;
import id.ac.ui.cs.advprog.auth.service.decorator.ProfileDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.UserProfile;

public class BioDecorator extends ProfileDecorator {
    private String newBio;

    public BioDecorator(UserProfile profile, String newBio) {
    }

    @Override
    public void updateProfile() {
    }
}