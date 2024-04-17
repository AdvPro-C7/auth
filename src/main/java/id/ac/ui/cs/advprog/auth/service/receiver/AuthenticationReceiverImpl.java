package id.ac.ui.cs.advprog.auth.service.receiver;

import id.ac.ui.cs.advprog.auth.AppProperties;
import id.ac.ui.cs.advprog.auth.model.User;
import id.ac.ui.cs.advprog.auth.model.request.LoginRequest;
import id.ac.ui.cs.advprog.auth.model.request.RegisterRequest;
import id.ac.ui.cs.advprog.auth.repository.UserRepository;
import io.jsonwebtoken.*;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Component
public class AuthenticationReceiverImpl implements AuthenticationReceiver {
    private final UserRepository repo;
    private final AppProperties props;

    public AuthenticationReceiverImpl(UserRepository repo,
            AppProperties props) {
        this.repo = repo;
        this.props = props;
    }

    private String generateJWT(String name) {
        if (name == null) {
            return "";
        } else {
            var now = Instant.now();
            Instant expiration = now.plusSeconds(86400);
            var expirationDate = Date.from(expiration);

            return Jwts
                    .builder().setSubject(name)
                    .setIssuedAt(new Date())
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512, this.props.getSecret()).compact();
        }
    }

    private boolean validateJWT(@Nullable String jwt) {
        try {
            Jwts.parser().setSigningKey(this.props.getSecret()).parseClaimsJws(jwt);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean authenticateUser(LoginRequest httpRequest) {
        if (httpRequest == null || httpRequest.getId() == null || httpRequest.getPassword() == null) {
            return false;
        }

        String id = httpRequest.getId();

        Boolean existsByEmail = this.repo.existsByEmail(id);
        Boolean existsByNoTelp = this.repo.existsByNoTelp(id);

        if (!existsByEmail && !existsByNoTelp) {
            return false;
        }

        if (existsByEmail) {
            User savedUser = this.repo.findByEmail(id);
            return savedUser.getPassword().equals(httpRequest.getPassword());
        }

        if (existsByNoTelp) {
            User savedUser = this.repo.findByNoTelp(id);
            return savedUser.getPassword().equals(httpRequest.getPassword());
        }

        return false;
    }

    @Override
    public ResponseCookie createToken(String name) {
        return ResponseCookie.from(this.props.getKey(), generateJWT(name))
                .path("/").httpOnly(true).sameSite("None").maxAge(86400).build();
    }

    @Override
    public User getUserDetails(HttpServletRequest httpRequest) {
        var token = WebUtils.getCookie(httpRequest, this.props.getKey());
        String jwt = token != null ? token.getValue() : null;

        if (jwt == null || !validateJWT(jwt)) {
            return null;
        }

        String id = Jwts.parser().setSigningKey(this.props.getSecret()).parseClaimsJws(jwt).getBody().getSubject();

        Boolean existsByEmail = this.repo.existsByEmail(id);
        Boolean existsByNoTelp = this.repo.existsByNoTelp(id);

        if (existsByEmail) {
            return this.repo.findByEmail(id);
        }

        if (existsByNoTelp) {
            return this.repo.findByNoTelp(id);
        }

        return null;
    }

    @Override
    public Boolean insertUser(RegisterRequest httpRequest) {
        if (httpRequest == null || httpRequest.getName() == null || httpRequest.getEmailAddress() == null
                || httpRequest.getPhoneNumber() == null || httpRequest.getPassword() == null
                || httpRequest.getName().contains("#")) {
            return null;
        }

        Boolean existsByEmail = this.repo.existsByEmail(httpRequest.getEmailAddress());
        Boolean existsByNoTelp = this.repo.existsByNoTelp(httpRequest.getPhoneNumber());

        if (existsByEmail || existsByNoTelp) {
            return false;
        }

        this.repo
                .save(new User(httpRequest.getName(), httpRequest.getEmailAddress(), httpRequest.getPhoneNumber(),
                        httpRequest.getPassword()));

        return true;
    }
}
