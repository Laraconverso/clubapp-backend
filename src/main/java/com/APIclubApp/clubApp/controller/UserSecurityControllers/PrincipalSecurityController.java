package com.APIclubApp.clubApp.controller.UserSecurityControllers;

import com.APIclubApp.clubApp.controller.UserSecurityControllers.request.CreateUserSecurityDTO;
import com.APIclubApp.clubApp.model.UserSecurityModels.ERole;
import com.APIclubApp.clubApp.model.UserSecurityModels.RoleEntity;
import com.APIclubApp.clubApp.model.UserSecurityModels.UserEntity;
import com.APIclubApp.clubApp.repository.UserSecurityRepositories.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalSecurityController {

    @Autowired
    private UserSecurityRepository userSecurityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World NOT SECURED";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "Hello World SECURED";
    }

    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestBody CreateUserSecurityDTO createUserSecurityDTO){

        Set<RoleEntity> roles = createUserSecurityDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserSecurityDTO.getUsername())
                .password(passwordEncoder.encode(createUserSecurityDTO.getPassword()))
                .email(createUserSecurityDTO.getEmail())
                .roles(roles)
                .build();

        userSecurityRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }
    @DeleteMapping("/deleteuser")
    public String deletUser(@RequestParam String id){
        userSecurityRepository.deleteById(Long.parseLong(id));
        return "Se borro usuario con user_id:".concat(id);
    }
}
