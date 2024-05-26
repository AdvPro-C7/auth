package id.ac.ui.cs.advprog.auth.controller;

import id.ac.ui.cs.advprog.auth.dto.UserProfileUpdateDTO;
import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.builder.UserProfileManager;
import id.ac.ui.cs.advprog.auth.service.invoker.AuthenticationInvokerImpl;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
   
    private final AuthenticationInvokerImpl service;
    private final UserProfileManager userProfileManager;

    public ProfileController(AuthenticationInvokerImpl service, UserProfileManager userProfileManager) {
        this.service = service;
        this.userProfileManager = userProfileManager;
    }

    @PatchMapping(value = "/updateProfile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateProfile(
        @RequestBody UserProfileUpdateDTO updates) {

        User user = this.service.getUserDetails(updates.getUid()); // Retrieve the user using UID

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (updates.getName() != null) {
            user = userProfileManager.constructNameProfile(user, updates.getName());
        }
        if (updates.getBirthDate() != null) {
            user = userProfileManager.constructBirthDateProfile(user, updates.getBirthDate());
        }
        if (updates.getBio() != null) {
            user = userProfileManager.constructBioProfile(user, updates.getBio());
        }
        if (updates.getGender() != null) {
            user = userProfileManager.constructGenderProfile(user, updates.getGender());
        }
        if (updates.getPassword() != null) {
            user = userProfileManager.constructPasswordProfile(user, updates.getPassword());
        }
        if (updates.getPhoto() != null) {
            user = userProfileManager.constructPhotoProfile(user, updates.getPhoto());
        }
        
        return ResponseEntity.ok(user);
    }
}
