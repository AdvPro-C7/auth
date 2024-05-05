package id.ac.ui.cs.advprog.auth.controller;

import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.service.builder.UserProfileManager;
import id.ac.ui.cs.advprog.auth.service.builder.UserProfileUpdateDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserProfileManager userProfileManager;

    @PatchMapping(value = "/updateProfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> updateProfile(
        @RequestPart("updates") UserProfileUpdateDTO updates,
        @RequestBody User user) throws Exception {
    }
}
