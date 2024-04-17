package id.ac.ui.cs.advprog.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    // token
    @Value("${app.key}")
    private String key;
    @Value("${app.secret}")
    private String secret;
}
