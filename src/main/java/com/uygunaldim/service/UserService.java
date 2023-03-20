package com.uygunaldim.service;

import com.uygunaldim.data.dto.UserDto;
import com.uygunaldim.data.dto.request.RoleRequest;
import com.uygunaldim.data.dto.request.UserRequest;
import com.uygunaldim.data.entity.Role;
import com.uygunaldim.data.entity.User;
import com.uygunaldim.exception.AlreadyExistsException;
import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.uygunaldim.util.ApplicationConstants.USER_BAD_REQUEST;
import static com.uygunaldim.util.ApplicationConstants.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserDto::of).toList();
    }

    public UserDto getUserById(Long id) {
        return UserDto.of(findUserById(id));
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND, "User could not found by id: " + id));
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND, "User could not found by username: " + username));
    }

    public UserDto updateUser(UserRequest request) {
        User user = findUserById(request.getId());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(Role.of(request.getRole()));
        return UserDto.of(userRepository.save(user));
    }

    public UserDto createUser(UserRequest request) {
        if (isUserExistsWithUsername(request)) {
            throw new AlreadyExistsException(USER_BAD_REQUEST, "User already exists with username: " + request.getUsername());
        }

        if(isUserExistsWithEmail(request)) {
            throw new AlreadyExistsException(USER_BAD_REQUEST, "User already exists with email: " + request.getEmail());
        }

        return UserDto.of(
            userRepository.save(User.builder()
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .role(Role.of(roleService.getRoleOrCreateIfNotExists(RoleRequest.builder()
                            .name(request.getRole().getName())
                            .build())))
                    .build())
        );
    }

    public String deleteUser(Long id) {
        String username = findUserById(id).getUsername();
        userRepository.deleteById(id);
        return "User with username: " + username + " is deleted!";
    }

    private boolean isUserExistsWithUsername(UserRequest request) {
        return userRepository.existsByUsername(request.getUsername());
    }

    private boolean isUserExistsWithEmail(UserRequest request) {
        return userRepository.existsByEmail(request.getEmail());
    }
}
