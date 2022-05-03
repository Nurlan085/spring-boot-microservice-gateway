package az.dev.gateway.service;

import az.dev.gateway.dto.request.UserReqDto;

public interface AuthenticationService {

    String signInAndReturnJwt(UserReqDto request);
}
