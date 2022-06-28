package io.github.edsontofolo.api.config;

import io.github.edsontofolo.api.domain.User;
import io.github.edsontofolo.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
@RequiredArgsConstructor
public class LocalConfig {

    private final UserRepository userRepository;

    @Bean
    public void startDb() {
         var edson = new User(null, "Edson", "edson@gmail.com", "1234");
         var luiz = new User(null, "Luiz", "luiz@gmail.com", "1234");
         this.userRepository.saveAll(List.of(edson, luiz));
    }
}
