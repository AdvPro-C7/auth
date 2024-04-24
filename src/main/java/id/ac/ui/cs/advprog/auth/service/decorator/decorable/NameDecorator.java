package id.ac.ui.cs.advprog.auth.service.decorator.decorable;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.decorator.Profile;
import id.ac.ui.cs.advprog.auth.service.decorator.ProfileDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.UserProfile;

public class NameDecorator extends ProfileDecorator {
    private String newName;

    public NameDecorator(UserProfile profile, String newName) {
    }

    @Override
    public void updateProfile() {
    }
}