package az.dev.gateway.service.impl;

import az.dev.gateway.dto.request.UserReqDto;
import az.dev.gateway.model.User;
import az.dev.gateway.repository.UserRepository;
import az.dev.gateway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(UserReqDto userReqDto) {
        User user = User.builder()
                .username(userReqDto.getUsername())
                .password(passwordEncoder.encode(userReqDto.getPassword()))
                .name(userReqDto.getName())
                .createTime(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
