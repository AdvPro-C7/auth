package id.ac.ui.cs.advprog.auth.model;

import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;

import static org.junit.jupiter.api.Assertions.*;

class AllModelsTest {
    @Test
    void testUserFields() {
        User user = new User("bryan", "whoami@hello.com", "000888", "helloworld");
        assertEquals("bryan", user.getNama());
        assertEquals("whoami@hello.com", user.getEmail());
        assertEquals("000888", user.getNoTelp());
        assertEquals("helloworld", user.getPassword());
    }

    @Test
    void testLoginRequestFields() {
        LoginRequest loginRequest = new LoginRequest("whoami@hello.com", "helloworld");
        assertEquals("whoami@hello.com", loginRequest.getId());
        assertEquals("helloworld", loginRequest.getPassword());
    }

    @Test
    void testRegisterRequestFields() {
        RegisterRequest registerRequest = new RegisterRequest("John Doe", "whoami@hello.com", "000888",
                "helloworld");
        assertEquals("John Doe", registerRequest.getName());
        assertEquals("whoami@hello.com", registerRequest.getEmailAddress());
        assertEquals("000888", registerRequest.getPhoneNumber());
        assertEquals("helloworld", registerRequest.getPassword());
    }
}