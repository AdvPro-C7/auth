package id.ac.ui.cs.advprog.auth.service.builder;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class UserProfileUpdateDTO {
    private String name;
    private LocalDate birthDate;
    private String bio;
    private String gender;
    private String password;
    private MultipartFile photo;

    public UserProfileUpdateDTO() {}

    public UserProfileUpdateDTO(String name, LocalDate birthDate, String bio, String gender, String password, MultipartFile photo) {
    }

    // Setters
    public void setName(String newName) {
    }
    public void setBirthDate(LocalDate newDate) {
    }
    public void setBio(String newBio) {
    }
    public void setGender(String newGender) {
    }
    public void setPassword(String newPassword) {
    }
    public void setPhoto(MultipartFile newPhoto) {
    }

    // Getters
    public String getName() {
    }
    public LocalDate getBirthDate() {
    }
    public String getBio() {
    }
    public String getGender() {
    }
    public String getPassword() {
    }
    public MultipartFile getPhoto() {
    }
}
