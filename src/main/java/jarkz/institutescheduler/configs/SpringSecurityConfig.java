package jarkz.institutescheduler.configs;

import jarkz.institutescheduler.entities.User;
import jarkz.institutescheduler.models.AdministratorRepository;
import jarkz.institutescheduler.models.StudentRepository;
import jarkz.institutescheduler.models.TeacherRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(
      StudentRepository studentRepository,
      TeacherRepository teacherRepository,
      AdministratorRepository administratorRepository) {
    return username -> {
      return studentRepository
          .findByUsername(username)
          .map(student -> (User) student)
          .or(() -> teacherRepository.findByUsername(username))
          .or(() -> administratorRepository.findByUsername(username))
          .orElseThrow(
              () ->
                  new UsernameNotFoundException(
                      String.format("User %s is unregistered in system.", username)));
    };
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .authorizeHttpRequests(
            (authorizeHttpRequests) ->
                authorizeHttpRequests
                    .requestMatchers("/calendar")
                    .hasAnyAuthority("ADMINISTRATOR", "TEACHER", "STUDENT")
                    .requestMatchers("/schedule-creator")
                    .hasAnyAuthority("TEACHER")
                    .requestMatchers("/schedule-info")
                    .hasAnyAuthority("ADMINISTRATOR", "TEACHER", "STUDENT")
                    .requestMatchers("/", "/**", "/home", "/login")
                    .permitAll())
        .formLogin(
            (formLogin) ->
                formLogin
                    .loginPage("/login")
                    .usernameParameter("username")
                    .defaultSuccessUrl("/calendar")
                    .failureHandler(
                        (request, response, exception) -> {
                          String path = request.getContextPath();
                          String responseRedirectUrl =
                              path + "/login?error=" + exception.getMessage();
                          response.sendRedirect(responseRedirectUrl);
                        }))
        .logout(
            (logoutCustomizer) ->
                logoutCustomizer
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/login"))
        .build();
  }
}
