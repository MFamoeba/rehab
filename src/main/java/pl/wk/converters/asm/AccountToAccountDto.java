package pl.wk.converters.asm;

import pl.wk.dto.AccountDto;
import pl.wk.entities.asm.entity.Account;

import java.util.List;

public class AccountToAccountDto {
    public static  AccountDto toAccountDto(Account account){
        return new AccountDto(account.getId(), account.getUsername(), account.getEmail(),account.getGender(),
                account.getFirstName()
                , account.getLastName());
    }
    public static List<AccountDto> accountDtoList(List<Account>accounts){
        return accounts.stream().map(AccountToAccountDto::toAccountDto).toList();
    }
}
