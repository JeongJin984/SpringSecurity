package com.example.SpringSecurity2.Security.config;

import com.example.SpringSecurity2.Security.common.AjaxLoginAuthenticationEntryPoint;
import com.example.SpringSecurity2.Security.filter.AjaxLoginProcessingFilter;
import com.example.SpringSecurity2.Security.handler.AjaxAccessDeniedHandler;
import com.example.SpringSecurity2.Security.handler.AjaxAuthenticationFailureHandler;
import com.example.SpringSecurity2.Security.handler.AjaxAuthenticationSuccessHandler;
import com.example.SpringSecurity2.Security.provider.AjaxAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(0)
public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
//        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
//        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean());
//        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler);
//        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler);
//        return ajaxLoginProcessingFilter;
//    }

    @Bean
    public AjaxAuthenticationProvider ajaxAuthenticationProvider() { return new AjaxAuthenticationProvider(); }

    @Autowired
    AjaxAccessDeniedHandler ajaxAccessDeniedHandler;

    @Autowired
    AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ajaxAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/messages").hasRole("MANAGER")
                .anyRequest().authenticated()
//        .and()
//                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
        ;

        http
                .exceptionHandling()
                .authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint())
                .accessDeniedHandler(ajaxAccessDeniedHandler)
        ;

        http.csrf().disable();

        customConfigurerAjax(http);
    }

    private void customConfigurerAjax(HttpSecurity http) throws Exception {
        http
                .apply(new AjaxLoginConfigurer<>())
                .successHandlerAjax(ajaxAuthenticationSuccessHandler)
                .failureHandlerAjax(ajaxAuthenticationFailureHandler)
                .setAuthenticationManager(authenticationManagerBean())
                .loginProcessingUrl("/api/login")
        ;
    }

}
