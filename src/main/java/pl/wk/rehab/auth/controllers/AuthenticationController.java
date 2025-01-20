package pl.wk.rehab.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wk.rehab.auth.dto.LoginRequest;
import pl.wk.rehab.auth.dto.RegisterUserRequest;
import pl.wk.rehab.auth.services.AuthenticationService;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/user")
    public ResponseEntity<String> registerUser(
            @RequestBody RegisterUserRequest request
    ) {
        return ResponseEntity.ok(service.registerUser(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.authenticate(request));
    }
}
