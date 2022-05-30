package com.epam.cv;

import com.epam.cv.entity.Role;
import com.epam.cv.entity.User;
import com.epam.cv.repository.RoleRepository;
import com.epam.cv.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@EnableSwagger2
@SpringBootApplication
public class CvApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpFirewall allowUrlSemicolonHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true);
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowUrlEncodedPeriod(true);
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            Role adminRole = roleRepository.findByRole("ADMIN");
            if (adminRole == null) {
                Role newAdminRole = Role.builder().role("ADMIN").build();
                roleRepository.save(newAdminRole);
            }
            Role userRole = roleRepository.findByRole("USER");
            if (userRole == null) {
                Role newUserRole = Role.builder().role("USER").build();
                roleRepository.save(newUserRole);
            }
            User user = userRepository.findByFullName("admin");
            if (user == null) {
                Role role = roleRepository.findByRole("ADMIN");
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                User admin = User.builder().id("ADMIN").email("admin@gmail.com").password("admin").fullName("admin").roles(roles).build();
                userRepository.save(admin);
            }
        };
    }
}
