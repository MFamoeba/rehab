package pl.wk.controllers.asm;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wk.converters.asm.AccountToAccountDto;
import pl.wk.dto.AccountDto;
import pl.wk.entities.asm.entity.Account;
import pl.wk.services.asm.UserService;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    UserService userService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> getAllUsers() {
        return AccountToAccountDto.accountDtoList(userService.getAllUsers());
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto createUser( Account account) {
        return AccountToAccountDto.toAccountDto( userService.addUser(account));
    }
}
