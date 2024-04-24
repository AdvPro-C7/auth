package id.ac.ui.cs.advprog.auth.service.decorator;

public abstract class ProfileDecorator implements UserProfile {
    protected UserProfile decoratedProfile;

    public ProfileDecorator(UserProfile profile) {
        this.decoratedProfile = profile;
    }

    @Override
    public String getDetails() {
        return decoratedProfile.getDetails();
    }

    @Override
    public void updateProfile() {
        decoratedProfile.updateProfile();
    }
}
