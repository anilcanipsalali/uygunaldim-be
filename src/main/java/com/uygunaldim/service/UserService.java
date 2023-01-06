package com.uygunaldim.service;

import com.uygunaldim.dto.UserDto;
import com.uygunaldim.dto.request.UserRequest;
import com.uygunaldim.entity.Role;
import com.uygunaldim.entity.User;
import com.uygunaldim.exception.AlreadyExistsException;
import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserDto::of).toList();
    }

    public UserDto getUserById(Long id) {
        return UserDto.of(findUserById(id));
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UYGNALDM-USER-404", "User could not found by id: " + id));
    }

    public UserDto updateUser(UserRequest request) {
        User user = findUserById(request.getId());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(Role.of(request.getRole()));
        return UserDto.of(userRepository.save(user));
    }

    public UserDto createUser(UserRequest request) {
        if (isUserExistsWithUsername(request)) {
            throw new AlreadyExistsException("UYGNALDM-USER-400", "User already exists with username: " + request.getUsername());
        }

        if(isUserExistsWithEmail(request)) {
            throw new AlreadyExistsException("UYGNALDM-USER-400", "User already exists with email: " + request.getEmail());
        }

        return UserDto.of(
            userRepository.save(User.builder()
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .role(Role.of(request.getRole()))
                    .build())
        );
    }

    public String deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.deleteById(id);
        return "User with username: " + user.getUsername() + " is deleted!";
    }

    private boolean isUserExistsWithUsername(UserRequest request) {
        return userRepository.existsByUsername(request.getUsername());
    }

    private boolean isUserExistsWithEmail(UserRequest request) {
        return userRepository.existsByEmail(request.getEmail());
    }
}
