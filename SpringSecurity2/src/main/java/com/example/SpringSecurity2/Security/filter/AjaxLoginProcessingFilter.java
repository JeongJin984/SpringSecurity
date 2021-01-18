package com.example.SpringSecurity2.Security.filter;

import com.example.SpringSecurity2.Security.token.AjaxAuthenticationToken;
import com.example.SpringSecurity2.domain.dto.AccountDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public AjaxLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/api/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        if(!isAjax(httpServletRequest)) {
            throw new IllegalStateException("wefaesrcvasrfvr");
        }

        AccountDTO accountDTO = objectMapper.readValue(httpServletRequest.getReader(), AccountDTO.class);

        if (ObjectUtils.isEmpty(accountDTO.getUsername()) || ObjectUtils.isEmpty(accountDTO.getPassword())) {
            throw new IllegalArgumentException("Authentication method not supported");
        }

        AjaxAuthenticationToken authenticationToken = new AjaxAuthenticationToken(accountDTO.getUsername(), accountDTO.getPassword());

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Request-With");
        boolean equals = "XMLHttpRequest".equals(request.getHeader("X-Request-With"));
        return equals;
    }
}
