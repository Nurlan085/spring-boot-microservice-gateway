package az.dev.gateway.service;

import az.dev.gateway.dto.request.UserReqDto;
import az.dev.gateway.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(UserReqDto userReqDto);

    Optional<User> findByUsername(String username);

    List<User> findAllUsers();
}
