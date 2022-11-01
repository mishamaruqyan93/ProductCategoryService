package am.itspace.productcategoryservice.endpoint;

import am.itspace.productcategoryservice.dto.UserAuthDto;
import am.itspace.productcategoryservice.dto.UserAuthResponseDto;
import am.itspace.productcategoryservice.dto.UserRequestDto;
import am.itspace.productcategoryservice.mapper.UserMapper;
import am.itspace.productcategoryservice.model.Role;
import am.itspace.productcategoryservice.model.User;
import am.itspace.productcategoryservice.service.impl.UserServiceImpl;
import am.itspace.productcategoryservice.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserMapper userMapper;
    private final UserServiceImpl userServiceImpl;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody UserRequestDto userRequestDto) {
        Optional<User> userByEmail = userServiceImpl.findUserByEmail(userRequestDto.getEmail());
        if (userByEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = userMapper.map(userRequestDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userMapper.map(userServiceImpl.save(user)));
    }

    @PostMapping("/user/auth")
    public ResponseEntity<?> auth(@RequestBody UserAuthDto userAuthDto) {
        Optional<User> byEmail = userServiceImpl.findUserByEmail(userAuthDto.getEmail());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(UserAuthResponseDto.builder()
                        .token(jwtTokenUtil.generateToken(user.getEmail()))
                        .userDto(userMapper.map(user))
                        .build()
                );
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
