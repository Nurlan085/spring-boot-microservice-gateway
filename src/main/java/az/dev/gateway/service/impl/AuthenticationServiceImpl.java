package az.dev.gateway.service.impl;

import az.dev.gateway.dto.request.UserReqDto;
import az.dev.gateway.model.User;
import az.dev.gateway.security.UserPrincipal;
import az.dev.gateway.security.jwt.JwtProvider;
import az.dev.gateway.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public String signInAndReturnJwt(UserReqDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return jwtProvider.generateToken(userPrincipal);
    }
}
