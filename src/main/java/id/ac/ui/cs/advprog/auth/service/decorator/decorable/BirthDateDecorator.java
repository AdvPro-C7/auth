package id.ac.ui.cs.advprog.auth.service.decorator.decorable;

import java.time.LocalDate;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.decorator.Profile;
import id.ac.ui.cs.advprog.auth.service.decorator.ProfileDecorator;
import id.ac.ui.cs.advprog.auth.service.decorator.UserProfile;

public class BirthDateDecorator extends ProfileDecorator {
    private LocalDate newBirthDate;

    public BirthDateDecorator(UserProfile profile, LocalDate newBirthDate) {
        super(profile);
        this.newBirthDate = newBirthDate;
    }

    @Override
    public void updateProfile() {
        User user = ((Profile) decoratedProfile).getUser();
        user.setTanggalLahir(newBirthDate);
        System.out.println("Updating birth date to: " + newBirthDate);
        super.updateProfile();
    }
}

