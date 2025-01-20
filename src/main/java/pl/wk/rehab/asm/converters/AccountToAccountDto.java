package pl.wk.rehab.asm.converters;

import pl.wk.rehab.asm.dto.AccountDto;
import pl.wk.rehab.entities.asm.entity.Account;

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
