package id.ac.ui.cs.advprog.auth.service.decorator.decorable;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.decorator.Profile;
import id.ac.ui.cs.advprog.auth.service.decorator.ProfileDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.UserProfile;

public class PhotoDecorator extends ProfileDecorator {
    private byte[] newPhoto;

    public PhotoDecorator(UserProfile profile, byte[] newPhoto) {
        super(profile);
        this.newPhoto = newPhoto;
    }

    @Override
    public void updateProfile() {
        User user = ((Profile) decoratedProfile).getUser();
        user.setFoto(newPhoto);
        System.out.println("Updating profile photo.");
        super.updateProfile();
    }
}

