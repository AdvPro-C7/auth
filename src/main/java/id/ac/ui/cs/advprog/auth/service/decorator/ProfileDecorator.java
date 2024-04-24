package id.ac.ui.cs.advprog.auth.service.decorator;

public abstract class ProfileDecorator implements UserProfile {
    protected UserProfile decoratedProfile;

    public ProfileDecorator(UserProfile profile) {
    }

    @Override
    public String getDetails() {
    }

    @Override
    public void updateProfile() {
    }
}
