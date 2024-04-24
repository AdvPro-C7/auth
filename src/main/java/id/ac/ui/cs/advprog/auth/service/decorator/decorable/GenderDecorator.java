package id.ac.ui.cs.advprog.auth.service.decorator.decorable;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.decorator.Profile;
import id.ac.ui.cs.advprog.auth.service.decorator.ProfileDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.UserProfile;

public class GenderDecorator extends ProfileDecorator {
    private String newGender;

    public GenderDecorator(UserProfile profile, String newGender) {
    }

    @Override
    public void updateProfile() {
    }
}
