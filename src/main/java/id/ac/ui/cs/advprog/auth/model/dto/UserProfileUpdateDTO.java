package id.ac.ui.cs.advprog.auth.model.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileUpdateDTO {
    private String name;
    private LocalDate birthDate;
    private String bio;
    private String gender;
    private String password;
    private String photo;
    private String uid;

    public UserProfileUpdateDTO(String name, LocalDate birthDate, String bio, String gender, String password, String photo, String uid) {
        this.name = name;
        this.birthDate = birthDate;
        this.bio = bio; 
        this.gender = gender;
        this.password = password;
        this.photo = photo;
        this.uid = uid;
    }
}
