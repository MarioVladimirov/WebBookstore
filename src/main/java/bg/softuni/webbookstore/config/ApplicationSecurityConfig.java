package bg.softuni.webbookstore.config;

import bg.softuni.webbookstore.service.impl.BookstoreUserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BookstoreUserService bookstoreUserService;
    private final PasswordEncoder passwordEncoder;


    public ApplicationSecurityConfig(BookstoreUserService bookstoreUserService, PasswordEncoder passwordEncoder) {
        this.bookstoreUserService = bookstoreUserService;
        this.passwordEncoder = passwordEncoder;
    }

    //TODO
    //add antMatchers for new routes

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .antMatchers("/books/add", "/books/edit/**", "/books/delete/**",
                        "/authors/add", "/authors/edit/**", "/authors/delete/**",
                        "/roles/**").hasRole("ADMIN")
                    .antMatchers("/", "/home", "/users/login", "/users/register",
                            "/books/*", "/reviews/api").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/users/login")
                    .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                    .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                    .defaultSuccessUrl("/home")
                    .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                    .logoutUrl("/users/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/unauthorized");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(bookstoreUserService)
                .passwordEncoder(passwordEncoder);
    }
}
