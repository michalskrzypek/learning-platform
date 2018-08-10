package pl.michalskrzypek.LearningPlatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.michalskrzypek.LearningPlatform.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    private static final String LOGIN_URL = "/login";

    private static final String[] PERMIT_URLS = {
            LOGIN_URL,
            "/*.css",
            "/resources/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().authenticated().and().formLogin().and().logout().permitAll();


           /*     .antMatchers(PERMIT_URLS).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl(LOGIN_URL).loginPage(LOGIN_URL).permitAll()
                .and().oauth2Login().and()
                .logout().permitAll();


                .successHandler(authenticationSuccessHandler)
                .failureHandler(new AuthenticationFailureHandler())
                .and().logout()
                .logoutSuccessHandler(noRedirectAfterLogoutHandler)
                .permitAll();

      http
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint(LOGIN_URL));*/
    }

    /*    @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(createActiveDirectoryAuthProvider());
        }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("user").password("123").roles("USER");
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("inst").password("123").roles("INSTRUCTOR");
        auth.userDetailsService(userService);
    }

}
