package id.ac.ui.cs.advprog.auth.service.decorator.decorable;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.decorator.Profile;
import id.ac.ui.cs.advprog.auth.service.decorator.ProfileDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.UserProfile;

public class GenderDecorator extends ProfileDecorator {
    private String newGender;

    public GenderDecorator(UserProfile profile, String newGender) {
        super(profile);
        this.newGender = newGender;
    }

    @Override
    public void updateProfile() {
        User user = ((Profile) decoratedProfile).getUser();
        user.setJenisKelamin(newGender);
        System.out.println("Updating gender to: " + newGender);
        super.updateProfile();
    }
}
