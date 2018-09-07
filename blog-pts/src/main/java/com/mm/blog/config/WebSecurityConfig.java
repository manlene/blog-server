package com.mm.blog.config;

import com.mm.blog.security.CustomAuthenticationEntryPoint;
import com.mm.blog.security.CustomAuthenticationFailureHandler;
import com.mm.blog.security.CustomAuthenticationSuccessHandler;
import com.mm.blog.util.Md5;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Auther: manman
 * @Date: 2018/9/6 11:10
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        http.csrf().disable()
                .anonymous().disable()
                .cors().and().httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(new CustomAuthenticationSuccessHandler())
                .failureHandler(new CustomAuthenticationFailureHandler())
                .and()
                .logout()
                .permitAll()
                ;

}
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return  new PasswordEncoder() {
            public String encode(CharSequence rawPassword) {
                return Md5.encrypt(rawPassword.toString());
            }

            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(Md5.encrypt(rawPassword.toString()));
            }
        };
    }

}
