package br.com.leopoldodev.api.config;

import br.com.leopoldodev.api.domain.User;
import br.com.leopoldodev.api.repositories.UserRepository;
import br.com.leopoldodev.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
@AllArgsConstructor
public class LocalConfig {


    private UserRepository userRepository;

    @Bean
    public void initDB() {
        User u1 = new User(null, "Leo1", "leo1@gmail.com", "123");
        User u2 = new User(null, "Leo2", "leo2@gmail.com", "123");

        userRepository.saveAll(List.of(u1,u2));
    }



}
