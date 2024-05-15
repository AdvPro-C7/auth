package id.ac.ui.cs.advprog.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.service.invoker.AuthenticationInvokerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.atLeastOnce;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
        @Autowired
        private AuthenticationController controller;

        @Mock
        private AuthenticationInvokerImpl service;

        private MockMvc design;

        @BeforeEach
        public void setUp() {
                ReflectionTestUtils.setField(this.controller, "service", this.service);
                this.design = MockMvcBuilders.standaloneSetup(this.controller).build();
        }

        @Test
        void testRegisterUserSuccess() throws Exception {
                // Arrange
                Mockito.when(this.service.insertUser(Mockito.any(RegisterRequest.class))).thenReturn(true);

                // Act and Assert
                this.design.perform(post("/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(
                                                new RegisterRequest("bryan", "bryanilman20@gmail.com", "00008888",
                                                                "helloworld"))))
                                .andExpect(status().isOk())
                                .andExpect(content().string("{\"message\":\"registration successful\"}"));

                // Verify
                Mockito.verify(this.service, atLeastOnce()).insertUser(Mockito.any(RegisterRequest.class));
        }

        @Test
        void testRegisterUserBadRequest() throws Exception {
                // Arrange
                Mockito.when(this.service.insertUser(Mockito.any(RegisterRequest.class))).thenReturn(false);

                // Act and Assert
                this.design.perform(post("/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(
                                                new RegisterRequest("bryan", "bryanilman20@gmail.com", "00008888",
                                                                "helloworld"))))
                                .andExpect(status().isBadRequest())
                                .andExpect(content().string("{\"message\":\"registration failed\"}"));

                // Verify
                Mockito.verify(this.service, atLeastOnce()).insertUser(Mockito.any(RegisterRequest.class));
        }

        @Test
        void testLoginUserSuccess() throws Exception {
                // Arrange
                var loginRequest = new LoginRequest("bryanilman20@gmail.com", "helloworld");
                Mockito.when(this.service.authenticateUser(Mockito.any(LoginRequest.class))).thenReturn(true);

                ResponseCookie token = ResponseCookie.from("buku-id-session", "jwt").build();
                Mockito.when(this.service.createToken(loginRequest.getId())).thenReturn(token);

                // Act and Assert
                this.design.perform(post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                                .andExpect(status().isOk())
                                .andExpect(content().string("{\"message\":\"login successful\"}"));

                // Assert
                Mockito.verify(this.service, atLeastOnce()).authenticateUser(Mockito.any(LoginRequest.class));
        }

        @Test
        void testLogUserInBadRequest() throws Exception {
                // Arrange
                Mockito.when(this.service.authenticateUser(Mockito.any(LoginRequest.class))).thenReturn(false);

                // Act
                this.design.perform(post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper()
                                                .writeValueAsString(new LoginRequest("bryanilman20@gmail.com",
                                                                "helloworld"))))
                                .andExpect(status().isBadRequest())
                                .andExpect(content().string("{\"message\":\"invalid credentials\"}"));

                // Assert
                Mockito.verify(this.service, atLeastOnce()).authenticateUser(Mockito.any(LoginRequest.class));
        }

        @Test
        void testAuthAndGetUserDetails() throws Exception {
                // Arrange
                var userDetails = new User("bryan", "bryanilman20@gmail.com", "00008888", "helloworld");
                Mockito.when(this.service.getUserDetails(Mockito.any(MockHttpServletRequest.class)))
                                .thenReturn(userDetails);

                // Act
                this.design.perform(get("/authenticate"))
                                .andExpect(status().isOk())
                                .andExpect(content().json(new ObjectMapper().writeValueAsString(userDetails)));

                // Assert
                Mockito.verify(this.service, atLeastOnce()).getUserDetails(Mockito.any(MockHttpServletRequest.class));
        }
}
