package id.ac.ui.cs.advprog.auth.service.builder;

import java.time.LocalDate;

public class UserProfileUpdateDTO {
    private String name;
    private LocalDate birthDate;
    private String bio;
    private String gender;
    private String password;
    private String photo;

    public UserProfileUpdateDTO() {}

    public UserProfileUpdateDTO(String name, LocalDate birthDate, String bio, String gender, String password, String photo) {
        this.name = name;
        this.birthDate = birthDate;
        this.bio = bio; 
        this.gender = gender;
        this.password = password;
        this.photo = photo;
    }

    // Setters
    public void setName(String newName) {
        this.name = newName;
    }
    public void setBirthDate(LocalDate newDate) {
        this.birthDate = newDate;
    }
    public void setBio(String newBio) {
        this.bio = newBio;
    }
    public void setGender(String newGender) {
        this.gender = newGender;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void setPhoto(String newPhoto) {
        this.photo = newPhoto;
    }

    // Getters
    public String getName() {
        return name;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public String getBio() {
        return bio;
    }
    public String getGender() {
        return gender;
    }
    public String getPassword() {
        return password;
    }
    public String getPhoto() {
        return photo;
    }
}
