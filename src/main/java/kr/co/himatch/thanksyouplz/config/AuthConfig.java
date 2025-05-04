package kr.co.himatch.thanksyouplz.config;

import io.jsonwebtoken.io.Decoders;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import kr.co.himatch.thanksyouplz.auth.util.*;
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auth")
@Getter
@Setter
public class AuthConfig {
    private String emailId;
    private String emailPw;
    private String phone;
    private String serverUrl;
    private String redirectUrl;
    private Map<String, Credentials> credentials;
    private String coolsmsapikey;
    private String coolsmssecretkey;
    private String licenseKey;


    @PostConstruct
    public void init() {
        JwtTokenUtils.keyBytes = Decoders.BASE64.decode(licenseKey);
    }
}
