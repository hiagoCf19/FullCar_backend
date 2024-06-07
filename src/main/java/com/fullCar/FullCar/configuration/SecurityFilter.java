package com.fullCar.FullCar.configuration;

import com.fullCar.FullCar.service.AuthService;
import com.fullCar.FullCar.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final AuthService authService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var JWT= tokenService.getToken(request);
        if(JWT != null){
            var userDetails= tokenService.recoverContentToken(JWT);
            var account= authService.getUserByLogin(userDetails);
            var authentication = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(userDetails);
        }
        filterChain.doFilter(request, response);
    }

}
