package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

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

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().authenticated().and().formLogin().permitAll().and().logout().permitAll();


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
        auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");
        auth.inMemoryAuthentication().withUser("inst").password("123").roles("INSTRUCTOR");
        auth.userDetailsService(userService);
    }

}
