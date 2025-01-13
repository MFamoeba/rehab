package pl.wk.services.mauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.wk.entities.asm.entity.Account;
import pl.wk.repositories.asm.UserRepository;
import pl.wk.request.mauth.LoginRequest;
import pl.wk.request.mauth.RegisterUserRequest;
import pl.wk.security.JwtService;
import pl.wk.services.asm.UserService;

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
