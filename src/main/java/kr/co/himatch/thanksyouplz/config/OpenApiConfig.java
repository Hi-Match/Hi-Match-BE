package kr.co.himatch.thanksyouplz.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Your API Title", version = "v1"),
        servers = {
                @Server(url = "https://www.himatch.co.kr/himatch", description = "Production Server"),
                @Server(url = "http://localhost:8080/himatch", description = "Local Development Server")
        }
)
public class OpenApiConfig {
}
