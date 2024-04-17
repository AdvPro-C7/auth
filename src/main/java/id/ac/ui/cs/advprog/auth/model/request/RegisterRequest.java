package id.ac.ui.cs.advprog.auth.model.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String emailAddress;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Size(min = 8, max = 128)
    private String password;

    public RegisterRequest(String name, String emailAddress, String phoneNumber, String password) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
