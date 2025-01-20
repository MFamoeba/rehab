package pl.wk.rehab.asm.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wk.rehab.asm.repositories.RoleRepository;
import pl.wk.rehab.entities.asm.entity.Account;
import pl.wk.rehab.asm.repositories.UserRepository;
import pl.wk.rehab.entities.asm.entity.Role;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    public List<Account> getAllUsers(){
        return userRepository.findAll();
    }
    @Transactional
    public Account addUser(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return userRepository.save(account);
    }
    public Account addRoleToAccount(UUID id, String roleName){
        Role role = roleRepository.findByName(roleName);
        Account account = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for id: " + id));
        if(account.getRoles().isEmpty()){
            account.addRole(role);
            return userRepository.save(account);
        }
        else if(account.getRoles().size() == 2){
            throw new IllegalArgumentException("This account has maximum amount of roles");
        }
        else if (Objects.equals(account.getRoles().getFirst().getName(), "ADMIN")
                && Objects.equals(roleName, "PATIENT")) {
            throw new IllegalArgumentException("ADMIN cannot has role PATIENT");
        }
        if (Objects.equals(account.getRoles().getFirst().getName(), "ADMIN")
                && Objects.equals(roleName, "ADMIN")) {
            throw new IllegalArgumentException("This account alread has role ADMIN");
        }
        else if (Objects.equals(account.getRoles().getFirst().getName(), "MANAGER")
                && Objects.equals(roleName, "PATIENT")) {
            throw new IllegalArgumentException("MANAGER cannot has role PATIENT");
        }
        else if (Objects.equals(account.getRoles().getFirst().getName(), "MANAGER")
                && Objects.equals(roleName, "MANAGER")) {
            throw new IllegalArgumentException("This account alread has role MANAGER");
        }
        else if (Objects.equals(account.getRoles().getFirst().getName(), "PATIENT")&&(
                Objects.equals(roleName, "ADMIN")
                        ||  Objects.equals(roleName, "MANAGER"))) {
            throw new IllegalArgumentException("PATIENT cannot has any other role");
        }

        else{
            account.addRole(role);
            return userRepository.save(account);
        }
    }
    public Account takeRole(UUID id, String roleName){
        Role role = roleRepository.findByName(roleName);
        Account account = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for id: " + id));
        for(Role roles : account.getRoles()){
            if(roles.getName().equals(roleName)){
                account.removeRole(role);
                return userRepository.save(account);
            }
        }
        throw new IllegalArgumentException("This account does not have role "+roleName);
    }


}
