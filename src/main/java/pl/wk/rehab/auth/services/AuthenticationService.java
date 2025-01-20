package pl.wk.rehab.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.wk.rehab.entities.asm.entity.Account;
import pl.wk.rehab.asm.repositories.UserRepository;
import pl.wk.rehab.auth.dto.LoginRequest;
import pl.wk.rehab.auth.dto.RegisterUserRequest;
import pl.wk.rehab.config.security.JwtService;
import pl.wk.rehab.asm.services.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService accountService;
    private final UserRepository accountRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public String registerUser(RegisterUserRequest request) {
        Account account = new Account(request.getUsername(), request.getPassword(), request.getEmail()
                , request.getGender(), request.getFirstName(), request.getLastName());
        accountService.addUser(account);
        return jwtService.generateToken(account);
    }

    public String authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = accountRepository.findByUsername(request.getUsername());
        return jwtService.generateToken(user);
    }

}
